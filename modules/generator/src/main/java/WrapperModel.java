import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.MethodModel;
import net.sf.jwrappers.generator.model.Model;
import net.sf.jwrappers.generator.model.code.AttributeExpression;
import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.ReturnInstruction;

public class WrapperModel {
    private final Model model = new Model();
    private String packageName;
    private MType defaultException;
    private ClassModel wrapperFactory;
    private List<WrapperClassModel> wrapperClasses = new LinkedList<WrapperClassModel>();
    
    public Model getModel() {
        return model;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public MType getDefaultException() {
        return defaultException;
    }

    public void setDefaultExceptionType(Class<?> defaultExceptionType) {
        defaultException = model.importType(defaultExceptionType);
    }
    
    public WrapperClassModel addInterface(Class<?> iface) {
        WrapperClassModel wrapperClass = new WrapperClassModel(this, iface);
        wrapperClasses.add(wrapperClass);
        return wrapperClass;
    }
    
    public ClassModel getWrapperFactory() {
        return wrapperFactory;
    }

    public void build() {
        wrapperFactory = model.createClass(new ClassName(packageName, "WrapperFactory"));
        Attribute allowUnwrapAttribute = wrapperFactory.createAttribute("allowUnwrap", model.importType(Boolean.TYPE));
        MethodModel isAllowUnwrapMethod = wrapperFactory.createMethod("isAllowUnwrap");
        isAllowUnwrapMethod.setReturnType(model.importType(Boolean.TYPE));
        isAllowUnwrapMethod.getCode().addInstruction(new ReturnInstruction(new AttributeExpression(Expression.SELF, allowUnwrapAttribute)));
        for (WrapperClassModel wrapperClass : wrapperClasses) {
            wrapperClass.build();
        }
    }
}
