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
import net.sf.jwrappers.generator.model.Model;
import net.sf.jwrappers.generator.model.code.AttributeExpression;
import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.IfStatement;
import net.sf.jwrappers.generator.model.code.MethodInvocation;
import net.sf.jwrappers.generator.model.code.ReturnInstruction;
import net.sf.jwrappers.generator.model.code.SourceStatement;
import net.sf.jwrappers.generator.model.javadoc.JavadocModel;

public class WrapperClassModel {
    private final WrapperModel wrapperModel;
    private final Model model;
    private final ClassModel targetClass;
    private ClassModel wrapperClass;
    private Attribute wrapperFactoryAttribute;
    private Attribute targetAttribute;
    private MethodModel initMethod;
    private List<Attribute> relations = new LinkedList<Attribute>();
    
    public WrapperClassModel(WrapperModel wrapperModel, Class<?> iface) {
        this.wrapperModel = wrapperModel;
        model = wrapperModel.getModel();
        targetClass = model.importClass(iface);
    }
    
    public List<Attribute> getRelations() {
        return relations;
    }

    public void addRelation(Class<?> clazz, String name) {
        MClassType type = (MClassType)model.importType(clazz);
        relations.add(getWrapperClass().createAttribute(Access.PACKAGE, name, type));
    }
    
    public ClassModel getWrapperClass() {
        if (wrapperClass == null) {
            wrapperClass = model.createClass(new ClassName(wrapperModel.getPackageName(), targetClass.getName().getUnqualifiedName() + "Wrapper"));
            wrapperClass.addInterface(new MClassType(targetClass));
        }
        return wrapperClass;
    }
    
    public ClassModel getTargetClass() {
        return targetClass;
    }
    
    public MType getWrapperClassType() {
        return new MClassType(getWrapperClass());
    }
    
    public Attribute getWrapperFactoryAttribute() {
        if (wrapperFactoryAttribute == null) {
            wrapperFactoryAttribute = wrapperClass.createAttribute(Access.PACKAGE, "wrapperFactory", new MClassType(wrapperModel.getWrapperFactory()));
        }
        return wrapperFactoryAttribute;
    }
    
    public Attribute getTargetAttribute() {
        if (targetAttribute == null) {
            targetAttribute = wrapperClass.createAttribute(Access.PACKAGE, "parent", new MClassType(targetClass));
        }
        return targetAttribute;
    }

    public MethodModel getInitMethod() {
        if (initMethod == null) {
            initMethod = wrapperClass.createMethod("init");
            initMethod.setAccess(Access.PROTECTED);
            initMethod.addException(wrapperModel.getDefaultException());
            JavadocModel javadoc = initMethod.getJavadoc();
            javadoc.addText("Wrapper initialization method. This method is executed once before any\n");
            javadoc.addText("delegate method is called on the wrapper. Subclasses can override this\n");
            javadoc.addText("method to do initialization work. The default implementation does\n");
            javadoc.addText("nothing.\n");
            javadoc.addThrows(wrapperModel.getDefaultException(), wrapperModel.getDefaultExceptionDescription());
        }
        return initMethod;
    }

    public void build() {
        ClassModel wrapperClass = getWrapperClass();
        
        buildUnwrapMethod();
        
        List<MethodModel> targetMethods = new ArrayList<MethodModel>(targetClass.getMethods());
        Collections.sort(targetMethods, new Comparator<MethodModel>() {
            public int compare(MethodModel o1, MethodModel o2) {
                return o1.getSignature().toString().compareTo(o2.getSignature().toString());
            }
        });
        outer:
        for (MethodModel targetMethod : targetMethods) {
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
    
    private void buildUnwrapMethod() {
        MethodModel unwrapMethod = wrapperClass.createMethod("unwrap");
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
        MethodInvocation invocation = new MethodInvocation(new AttributeExpression(Expression.SELF, targetAttribute), targetMethod);
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
                    MethodInvocation wrapInvocation = new MethodInvocation(new AttributeExpression(Expression.SELF, wrapperFactoryAttribute), wrapMethod);
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
