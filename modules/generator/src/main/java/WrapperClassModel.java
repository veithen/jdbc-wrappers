import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.Access;
import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.model.Argument;
import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.MethodModel;
import net.sf.jwrappers.generator.model.JavaModel;
import net.sf.jwrappers.generator.model.code.AttributeExpression;
import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.IfStatement;
import net.sf.jwrappers.generator.model.code.MethodInvocation;
import net.sf.jwrappers.generator.model.code.ReturnInstruction;
import net.sf.jwrappers.generator.model.code.SourceStatement;
import net.sf.jwrappers.generator.model.javadoc.JavadocModel;

public class WrapperClassModel {
    private final WrapperModel wrapperModel;
    private final JavaModel javaModel;
    private final ClassModel targetClass;
    private final List<Attribute> relations = new LinkedList<Attribute>();
    private final Holder<ClassModel> wrapperClassHolder = new Holder<ClassModel>();
    private final Holder<WrapperClassModel> baseWrapperHolder = new Holder<WrapperClassModel>();
    private final Holder<Attribute> wrapperFactoryAttributeHolder = new Holder<Attribute>();
    private final Holder<Attribute> targetAttributeHolder = new Holder<Attribute>();
    private final Holder<Attribute> baseWrapperAttributeHolder = new Holder<Attribute>();
    private final Holder<MethodModel> initMethodHolder = new Holder<MethodModel>();
    
    public WrapperClassModel(WrapperModel wrapperModel, Class<?> iface) {
        this.wrapperModel = wrapperModel;
        javaModel = wrapperModel.getJavaModel();
        targetClass = javaModel.importClass(iface);
    }
    
    public ClassModel getTargetClass() {
        return targetClass;
    }
    
    public List<Attribute> getRelations() {
        return relations;
    }

    public void addRelation(Class<?> clazz, String name) {
        MClassType type = (MClassType)javaModel.importType(clazz);
        relations.add(getWrapperClass().createAttribute(Access.PACKAGE, name, type));
    }
    
    public ClassModel getWrapperClass() {
        if (!wrapperClassHolder.isSet()) {
            ClassModel wrapperClass = javaModel.createClass(new ClassName(wrapperModel.getPackageName(), targetClass.getName().getUnqualifiedName() + "Wrapper"));
            wrapperClass.addInterface(new MClassType(targetClass));
            wrapperClassHolder.set(wrapperClass);
        }
        return wrapperClassHolder.get();
    }
    
    public MType getWrapperClassType() {
        return new MClassType(getWrapperClass());
    }
    
    public WrapperClassModel getBaseWrapper() {
        if (!baseWrapperHolder.isSet()) {
            List<MType> ifaces = targetClass.getInterfaces();
            if (ifaces.isEmpty()) {
                baseWrapperHolder.set(null);
            } else if (ifaces.size() > 1) {
                throw new Error();
            } else {
                baseWrapperHolder.set(wrapperModel.getWrapperClass(((MClassType)ifaces.get(0)).getName()));
            }
        }
        return baseWrapperHolder.get();
    }
    
    public Attribute getWrapperFactoryAttribute() {
        if (!wrapperFactoryAttributeHolder.isSet()) {
            wrapperFactoryAttributeHolder.set(getWrapperClass().createAttribute(Access.PACKAGE, "wrapperFactory", new MClassType(wrapperModel.getWrapperFactory())));
        }
        return wrapperFactoryAttributeHolder.get();
    }
    
    public Attribute getTargetAttribute() {
        if (!targetAttributeHolder.isSet()) {
            targetAttributeHolder.set(getWrapperClass().createAttribute(Access.PACKAGE, "parent", new MClassType(targetClass)));
        }
        return targetAttributeHolder.get();
    }
    
    public Attribute getBaseWrapperAttribute() {
        if (!baseWrapperAttributeHolder.isSet()) {
            WrapperClassModel baseWrapper = getBaseWrapper();
            if (baseWrapper == null) {
                baseWrapperAttributeHolder.set(null);
            } else {
                baseWrapperAttributeHolder.set(getWrapperClass().createAttribute(Access.PACKAGE, "baseWrapper", new MClassType(baseWrapper.getWrapperClass())));
            }
        }
        return baseWrapperAttributeHolder.get();
    }

    public MethodModel getInitMethod() {
        if (!initMethodHolder.isSet()) {
            MethodModel initMethod = getWrapperClass().createMethod("init");
            initMethod.setAccess(Access.PROTECTED);
            initMethod.addException(wrapperModel.getDefaultException());
            JavadocModel javadoc = initMethod.getJavadoc();
            javadoc.addText("Wrapper initialization method. This method is executed once before any\n");
            javadoc.addText("delegate method is called on the wrapper. Subclasses can override this\n");
            javadoc.addText("method to do initialization work. The default implementation does\n");
            javadoc.addText("nothing.\n");
            javadoc.addThrows(wrapperModel.getDefaultException(), wrapperModel.getDefaultExceptionDescription());
            initMethodHolder.set(initMethod);
        }
        return initMethodHolder.get();
    }

    public void build() {
        ClassModel wrapperClass = getWrapperClass();
        
        buildUnwrapMethod();
        
        WrapperClassModel baseWrapper = getBaseWrapper();
        if (baseWrapper != null) {
            List<MethodModel> baseWrapperMethods = new LinkedList<MethodModel>();
            while (baseWrapper != null) {
                baseWrapperMethods.addAll(baseWrapper.getTargetClass().getMethods());
                baseWrapper = baseWrapper.getBaseWrapper();
            }
            for (MethodModel baseWrapperMethod : sortMethods(baseWrapperMethods)) {
                MethodModel method = wrapperClass.overrideMethod(baseWrapperMethod);
                MethodInvocation invocation = new MethodInvocation(new AttributeExpression(Expression.SELF, getBaseWrapperAttribute()), baseWrapperMethod);
                for (Argument argument : method.getArguments()) {
                    invocation.addArgument(argument);
                }
                MType returnType = method.getReturnType();
                if (returnType != null) {
                    method.getCode().addInstruction(new ReturnInstruction(invocation));
                } else {
                    method.getCode().addInstruction(invocation);
                }
            }
        }
        
        outer:
        for (MethodModel targetMethod : sortMethods(targetClass.getMethods())) {
            // The method might already be defined because the interface redeclares a method
            // already defined in an interface it extends.
            if (wrapperClass.getMethod(targetMethod.getSignature()) == null) {
                MethodModel method = wrapperClass.overrideMethod(targetMethod);
                MType returnType = method.getReturnType();
                if (returnType != null) {
                    for (Attribute relationAttribute : relations) {
                        if (returnType.equals(relationAttribute.getType())) {
                            buildRelationGetter(method, targetMethod, relationAttribute);
                            continue outer;
                        }
                    }
                }
                buildDelegateMethod(method, targetMethod);
            }
        }
    }
    
    private static List<MethodModel> sortMethods(List<MethodModel> methods) {
        List<MethodModel> result = new ArrayList<MethodModel>(methods);
        Collections.sort(result, new Comparator<MethodModel>() {
            public int compare(MethodModel o1, MethodModel o2) {
                return o1.getSignature().toString().compareTo(o2.getSignature().toString());
            }
        });
        return result;
    }
    
    private void buildUnwrapMethod() {
        MethodModel unwrapMethod = getWrapperClass().createMethod("unwrap");
        unwrapMethod.setReturnType(new MClassType(targetClass));
        IfStatement ifStatement = new IfStatement(new MethodInvocation(new AttributeExpression(Expression.SELF, getWrapperFactoryAttribute()), wrapperModel.getIsAllowUnwrapMethod()));
        ifStatement.getIfClause().addInstruction(new ReturnInstruction(new AttributeExpression(Expression.SELF, getTargetAttribute())));
        ifStatement.getElseClause().addInstruction(new SourceStatement("throw new IllegalStateException(\"unwrap not allowed\")"));
        unwrapMethod.getCode().addInstruction(ifStatement);
    }
    
    private void buildRelationGetter(MethodModel method, MethodModel targetMethod, Attribute relationAttribute) {
        JavadocModel javadoc = method.getJavadoc();
        javadoc.addText("Delegate method for ");
        javadoc.addLink(targetMethod);
        javadoc.addText(".\n");
        javadoc.addText("This method returns the ");
        javadoc.addLink(wrapperModel.getWrapperClass(((MClassType)relationAttribute.getType()).getName()).getWrapperClass().getName());
        javadoc.addText(" object that\n");
        javadoc.addText("created this wrapper. For consistency reasons, it can't be\n");
        javadoc.addText("overridden.\n");
        javadoc.addText("\n");
        javadoc.addText("{@inheritDoc}\n");
        method.setFinal(true);
        method.getCode().addInstruction(new ReturnInstruction(new AttributeExpression(Expression.SELF, relationAttribute)));
    }
    
    private void buildDelegateMethod(MethodModel method, MethodModel targetMethod) {
        JavadocModel javadoc = method.getJavadoc();
        javadoc.addText("Delegate method for ");
        javadoc.addLink(targetMethod);
        javadoc.addText(".\n");
        MethodInvocation invocation = new MethodInvocation(new AttributeExpression(Expression.SELF, getTargetAttribute()), targetMethod);
        for (Argument argument : method.getArguments()) {
            invocation.addArgument(argument);
        }
        MType returnType = method.getReturnType();
        if (returnType != null) {
            Expression expression = invocation;
            if (returnType instanceof MClassType) {
                ClassName returnClass = ((MClassType)returnType).getName();
                MethodModel wrapMethod = wrapperModel.getWrapMethod(returnClass);
                if (wrapMethod != null) {
                    MethodInvocation wrapInvocation = new MethodInvocation(new AttributeExpression(Expression.SELF, getWrapperFactoryAttribute()), wrapMethod);
                    wrapInvocation.addArgument(expression);
                    for (Attribute relation : wrapperModel.getWrapperClass(returnClass).getRelations()) {
                        if (relation.getType().equals(new MClassType(targetClass))) {
                            wrapInvocation.addArgument(Expression.SELF);
                        } else {
                            wrapInvocation.addArgument(Expression.NULL); // TODO: not a sensible default value
                        }
                    }
                    expression = wrapInvocation;
                    javadoc.addText("This method wraps the ");
                    javadoc.addLink(returnClass);
                    javadoc.addText(" object using\n");
                    javadoc.addLink(wrapMethod);
                    javadoc.addText(".\n");
                }
            }
            method.getCode().addInstruction(new ReturnInstruction(expression));
        } else {
            method.getCode().addInstruction(invocation);
        }
        javadoc.addText("\n");
        javadoc.addText("{@inheritDoc}\n");
    }
}
