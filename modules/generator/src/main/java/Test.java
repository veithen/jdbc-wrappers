import java.io.OutputStreamWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.Model;
import net.sf.jwrappers.generator.model.Argument;
import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.MethodModel;
import net.sf.jwrappers.generator.model.code.AttributeExpression;
import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.MethodInvocation;
import net.sf.jwrappers.generator.model.code.ReturnInstruction;
import net.sf.jwrappers.generator.writer.CharStreamCodeWriter;

public class Test {
    public static void main(String[] args) {
        String packageName = "net.sf.jwrappers.jdbc";
        
        Model model = new Model();
        MType defaultException = model.importType(SQLException.class);
        
        ClassModel wrapperFactory = new ClassModel(new ClassName(packageName, "WrapperFactory"));
        Attribute allowUnwrapAttribute = wrapperFactory.createAttribute("allowUnwrap", model.importType(Boolean.TYPE));
        MethodModel isAllowUnwrapMethod = wrapperFactory.createMethod("isAllowUnwrap");
        isAllowUnwrapMethod.getCode().addInstruction(new ReturnInstruction(new AttributeExpression(Expression.SELF, allowUnwrapAttribute)));
        
        ClassModel targetClass = model.importClass(DataSource.class);
        
        ClassModel wrapperClass = new ClassModel(new ClassName(packageName, "DataSourceWrapper"));
        wrapperClass.addInterface(new MClassType(targetClass));
        Attribute wrapperFactoryAttribute = wrapperClass.createAttribute("wrapperFactory", new MClassType(wrapperFactory));
        Attribute targetAttribute = wrapperClass.createAttribute("parent", new MClassType(targetClass));
        
        MethodModel initMethod = wrapperClass.createMethod("init");
        initMethod.addException(defaultException);
        
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
        
        CharStreamCodeWriter codeWriter = new CharStreamCodeWriter(new OutputStreamWriter(System.out));
        wrapperClass.generate(codeWriter);
        codeWriter.flush();
    }
}
