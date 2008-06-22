import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.model.Argument;
import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.MethodModel;
import net.sf.jwrappers.generator.model.Model;
import net.sf.jwrappers.generator.model.code.AttributeExpression;
import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.MethodInvocation;
import net.sf.jwrappers.generator.model.code.ReturnInstruction;
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
        return wrapperClass;
    }

    public void build() {
        wrapperClass = model.createClass(new ClassName(wrapperModel.getPackageName(), targetClass.getName().getUnqualifiedName() + "Wrapper"));
        wrapperClass.addInterface(new MClassType(targetClass));

        Attribute wrapperFactoryAttribute = wrapperClass.createAttribute("wrapperFactory", new MClassType(wrapperModel.getWrapperFactory()));
        Attribute targetAttribute = wrapperClass.createAttribute("parent", new MClassType(targetClass));
        
        buildInitMethod();
        
        MethodModel unwrapMethod = wrapperClass.createMethod("unwrap");
        unwrapMethod.setReturnType(new MClassType(targetClass));
        
        Expression targetExpression = new AttributeExpression(Expression.SELF, targetAttribute);
        for (MethodModel targetMethod : targetClass.getMethods()) {
            MethodModel method = wrapperClass.overrideMethod(targetMethod);
            MethodInvocation invocation = new MethodInvocation(targetExpression, targetMethod);
            for (Argument argument : method.getArguments()) {
                invocation.addArgument(argument);
            }
            if (method.getReturnType() != null) {
                method.getCode().addInstruction(new ReturnInstruction(invocation));
            } else {
                method.getCode().addInstruction(invocation);
            }
        }
    }
    
    private void buildInitMethod() {
        MethodModel initMethod = wrapperClass.createMethod("init");
        initMethod.addException(wrapperModel.getDefaultException());
        JavadocModel javadoc = initMethod.getJavadoc();
        javadoc.addText("Wrapper initialization method. This method is executed once before any\n");
        javadoc.addText("delegate method is called on the wrapper. Subclasses can override this\n");
        javadoc.addText("method to do initialization work. The default implementation does\n");
        javadoc.addText("nothing.\n");
    }
}
