import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    public WrapperClassModel(WrapperModel wrapperModel, Class<?> iface) {
        this.wrapperModel = wrapperModel;
        model = wrapperModel.getModel();
        targetClass = model.importClass(iface);
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

    public void build() {
        ClassModel wrapperClass = getWrapperClass();
        
        Attribute wrapperFactoryAttribute = wrapperClass.createAttribute(Access.PACKAGE, "wrapperFactory", new MClassType(wrapperModel.getWrapperFactory()));
        Attribute targetAttribute = wrapperClass.createAttribute(Access.PACKAGE, "parent", new MClassType(targetClass));
        
        buildInitMethod();
        buildUnwrapMethod(wrapperFactoryAttribute, targetAttribute);
        
        Expression targetExpression = new AttributeExpression(Expression.SELF, targetAttribute);
        List<MethodModel> targetMethods = new ArrayList<MethodModel>(targetClass.getMethods());
        Collections.sort(targetMethods, new Comparator<MethodModel>() {
            public int compare(MethodModel o1, MethodModel o2) {
                return o1.getSignature().compareTo(o2.getSignature());
            }
        });
        for (MethodModel targetMethod : targetMethods) {
            MethodModel method = wrapperClass.overrideMethod(targetMethod);
            JavadocModel javadoc = method.getJavadoc();
            javadoc.addText("Delegate method for ");
            javadoc.addLink(targetMethod);
            javadoc.addText(".\n");
            MethodInvocation invocation = new MethodInvocation(targetExpression, targetMethod);
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

    private void buildInitMethod() {
        MethodModel initMethod = wrapperClass.createMethod("init");
        initMethod.setAccess(Access.PROTECTED);
        initMethod.addException(wrapperModel.getDefaultException());
        JavadocModel javadoc = initMethod.getJavadoc();
        javadoc.addText("Wrapper initialization method. This method is executed once before any\n");
        javadoc.addText("delegate method is called on the wrapper. Subclasses can override this\n");
        javadoc.addText("method to do initialization work. The default implementation does\n");
        javadoc.addText("nothing.\n");
        javadoc.addThrows(wrapperModel.getDefaultException(), wrapperModel.getDefaultExceptionDescription());
    }
    
    private void buildUnwrapMethod(Attribute wrapperFactoryAttribute, Attribute targetAttribute) {
        MethodModel unwrapMethod = wrapperClass.createMethod("unwrap");
        unwrapMethod.setReturnType(new MClassType(targetClass));
        IfStatement ifStatement = new IfStatement(new MethodInvocation(new AttributeExpression(Expression.SELF, wrapperFactoryAttribute), wrapperModel.getIsAllowUnwrapMethod()));
        ifStatement.getIfClause().addInstruction(new ReturnInstruction(new AttributeExpression(Expression.SELF, targetAttribute)));
        ifStatement.getElseClause().addInstruction(new SourceStatement("throw new IllegalStateException(\"unwrap not allowed\")"));
        unwrapMethod.getCode().addInstruction(ifStatement);
    }
}
