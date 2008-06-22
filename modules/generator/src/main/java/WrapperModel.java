import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.jwrappers.generator.Access;
import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.model.Argument;
import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.CodeModel;
import net.sf.jwrappers.generator.model.MethodModel;
import net.sf.jwrappers.generator.model.Model;
import net.sf.jwrappers.generator.model.code.ArgumentExpression;
import net.sf.jwrappers.generator.model.code.Assignment;
import net.sf.jwrappers.generator.model.code.AttributeExpression;
import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.MethodInvocation;
import net.sf.jwrappers.generator.model.code.NewExpression;
import net.sf.jwrappers.generator.model.code.ReturnInstruction;
import net.sf.jwrappers.generator.model.code.VariableDeclaration;
import net.sf.jwrappers.generator.model.code.VariableExpression;

public class WrapperModel {
    private final Model model = new Model();
    private String packageName;
    private MClassType defaultException;
    private String defaultExceptionDescription;
    private ClassModel wrapperFactory;
    private MethodModel isAllowUnwrapMethod;
    private Map<ClassName,WrapperClassModel> wrapperClasses = new LinkedHashMap<ClassName,WrapperClassModel>();
    private Map<ClassName,MethodModel> wrapMethods = new HashMap<ClassName,MethodModel>();
    
    public Model getModel() {
        return model;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public MClassType getDefaultException() {
        return defaultException;
    }

    public String getDefaultExceptionDescription() {
        return defaultExceptionDescription;
    }

    public void setDefaultExceptionType(Class<?> defaultExceptionType, String description) {
        defaultException = (MClassType)model.importType(defaultExceptionType);
        defaultExceptionDescription = description;
    }
    
    public WrapperClassModel addInterface(Class<?> iface) {
        WrapperClassModel wrapperClass = new WrapperClassModel(this, iface);
        wrapperClasses.put(wrapperClass.getTargetClass().getName(), wrapperClass);
        return wrapperClass;
    }
    
    public MethodModel getWrapMethod(ClassName targetClassName) {
        MethodModel wrapMethod = wrapMethods.get(targetClassName);
        if (wrapMethod == null) {
            WrapperClassModel wrapperClass = wrapperClasses.get(targetClassName);
            if (wrapperClass == null) {
                return null;
            }
            MType wrapperClassType = wrapperClass.getWrapperClassType();
            ClassModel wrapperFactory = getWrapperFactory();
            MethodModel createWrapperMethod = wrapperFactory.createMethod("create" + wrapperClass.getWrapperClass().getName().getUnqualifiedName());
            createWrapperMethod.setAccess(Access.PROTECTED);
            createWrapperMethod.setReturnType(wrapperClassType);
            createWrapperMethod.getCode().addInstruction(new ReturnInstruction(new NewExpression(wrapperClassType)));
            wrapMethod = wrapperFactory.createMethod("wrap" + targetClassName.getUnqualifiedName());
            wrapMethod.setReturnType(wrapperClassType);
            Argument targetArgument = wrapMethod.createArgument("parent", new MClassType(wrapperClass.getTargetClass()));
            wrapMethod.addException(defaultException);
            CodeModel code = wrapMethod.getCode();
            VariableDeclaration wrapperVariableDeclaration = new VariableDeclaration(wrapperClassType, "wrapper", new MethodInvocation(Expression.SELF, createWrapperMethod));
            code.addInstruction(wrapperVariableDeclaration);
            VariableExpression wrapperVariable = new VariableExpression(wrapperVariableDeclaration);
            code.addInstruction(new Assignment(new AttributeExpression(wrapperVariable, wrapperClass.getWrapperFactoryAttribute()), Expression.SELF));
            code.addInstruction(new Assignment(new AttributeExpression(wrapperVariable, wrapperClass.getTargetAttribute()), new ArgumentExpression(targetArgument)));
            code.addInstruction(new MethodInvocation(wrapperVariable, wrapperClass.getInitMethod()));
            code.addInstruction(new ReturnInstruction(wrapperVariable));
            wrapMethods.put(targetClassName, wrapMethod);
        }
        return wrapMethod;
    }
    
    public ClassModel getWrapperFactory() {
        if (wrapperFactory == null) {
            wrapperFactory = model.createClass(new ClassName(packageName, "WrapperFactory"));
            wrapperFactory.getJavadoc().addText("Wrapper factory base class.");
            Attribute allowUnwrapAttribute = wrapperFactory.createAttribute("allowUnwrap", model.importType(Boolean.TYPE));
            isAllowUnwrapMethod = wrapperFactory.createMethod("isAllowUnwrap");
            isAllowUnwrapMethod.setSynchonized(true);
            isAllowUnwrapMethod.setReturnType(model.importType(Boolean.TYPE));
            isAllowUnwrapMethod.getCode().addInstruction(new ReturnInstruction(new AttributeExpression(Expression.SELF, allowUnwrapAttribute)));
            MethodModel setAllowUnwrapMethod = wrapperFactory.createMethod("setAllowUnwrap");
            setAllowUnwrapMethod.setSynchonized(true);
            Argument allowUnwrapArgument = setAllowUnwrapMethod.createArgument("allowUnwrap", model.importType(Boolean.TYPE));
            setAllowUnwrapMethod.getCode().addInstruction(new Assignment(new AttributeExpression(Expression.SELF, allowUnwrapAttribute), new ArgumentExpression(allowUnwrapArgument)));
        }
        return wrapperFactory;
    }

    public MethodModel getIsAllowUnwrapMethod() {
        return isAllowUnwrapMethod;
    }

    public void build() {
        for (WrapperClassModel wrapperClass : wrapperClasses.values()) {
            getWrapMethod(wrapperClass.getTargetClass().getName());
            wrapperClass.build();
        }
    }
}
