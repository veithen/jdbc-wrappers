import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.writer.CharStreamCodeWriter;

public class Test {
    public static void main(String[] args) throws IOException {
        WrapperModel wrapperModel = new WrapperModel();
        wrapperModel.setPackageName("net.sf.jwrappers.jdbc");
        wrapperModel.setDefaultExceptionType(SQLException.class);
        wrapperModel.addInterface(DataSource.class);
        
        wrapperModel.build();
        
        File outputDir = new File("out");
        for (ClassModel classModel : wrapperModel.getModel().getClasses()) {
            ClassName className = classModel.getName();
            File dir = outputDir;
            for (String packageNamePart : className.getPackageName().split("\\.")) {
                dir = new File(dir, packageNamePart);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
            OutputStream out = new FileOutputStream(new File(dir, className.getUnqualifiedName() + ".java"));
            try {
                CharStreamCodeWriter codeWriter = new CharStreamCodeWriter(new OutputStreamWriter(out));
                classModel.generate(codeWriter);
                codeWriter.flush();
            }
            finally {
                out.close();
            }
        }
    }
}
