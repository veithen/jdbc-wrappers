package net.sf.jwrappers.generator.doclet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import net.sf.jwrappers.generator.ArrayType;
import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.PrimitiveType;
import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.JavaModel;
import net.sf.jwrappers.generator.model.MethodModel;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Type;

public class ModelGeneratorDoclet {
    private static File outFile;
    
    public static boolean start(RootDoc root) throws IOException {
        parseOptions(root.options());
        JavaModel javaModel = new JavaModel();
        for (ClassDoc classDoc : root.classes()) {
            try {
                importClass(javaModel, classDoc);
            }
            catch (Exception ex) {
                throw new Error("Exception caught while importing class " + classDoc.qualifiedName(), ex);
            }
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
        out.writeObject(javaModel);
        out.close();
        return true;
    }
    
    private static void parseOptions(String[][] options) {
        for (String[] option : options) {
            if (option[0].equals("-out")) {
                outFile = new File(option[1]);
                System.out.println("Output is going to " + outFile);
            }
        }
    }

    public static int optionLength(String option) {
        if (option.equals("-out")) {
            return 2;
        } else {
            return 0;
        }
    }
    
    public static boolean validOptions(String options[][], DocErrorReporter reporter) {
        boolean hasOut = false;
        for (String[] option : options) {
            String opt = option[0];
            if (opt.equals("-out")) {
                hasOut = true;
            }
        }
        if (!hasOut) {
            reporter.printError("No output file specified: -out <model output file>");
            return false;
        } else {
            return true;
        }
    }

    private static void importClass(JavaModel javaModel, ClassDoc classDoc) {
        ClassModel classModel = javaModel.createClass(getClassName(classDoc));
        for (MethodDoc methodDoc : classDoc.methods()) {
            importMethod(classModel, methodDoc);
        }
    }

    private static void importMethod(ClassModel classModel, MethodDoc methodDoc) {
        MethodModel methodModel = classModel.createMethod(methodDoc.name());
        Type returnType = methodDoc.returnType();
        if (!returnType.typeName().equals("void")) {
            methodModel.setReturnType(importType(returnType));
        }
        for (Parameter param : methodDoc.parameters()) {
            methodModel.createArgument(param.name(), importType(param.type()));
        }
    }
    
    private static ClassName getClassName(ClassDoc classDoc) {
        return new ClassName(classDoc.containingPackage().name(), classDoc.name());
    }
    
    private static MType importType(Type type) {
        MType result;
        if (type.isPrimitive()) {
            result = PrimitiveType.get(type.typeName());
        } else {
            result = new MClassType(getClassName(type.asClassDoc()));
        }
        int dimensions = type.dimension().length()/2;
        for (int i=0; i<dimensions; i++) {
            result = new ArrayType(result);
        }
        return result;
    }
}
