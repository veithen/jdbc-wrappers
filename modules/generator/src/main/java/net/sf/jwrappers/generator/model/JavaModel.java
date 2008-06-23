package net.sf.jwrappers.generator.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.ArrayType;
import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.PrimitiveType;
import net.sf.jwrappers.generator.writer.CharStreamCodeWriter;

public class JavaModel {
    private final List<ClassModel> classes = new LinkedList<ClassModel>();
    
    public List<ClassModel> getClasses() {
        return classes;
    }

    public ClassModel createClass(ClassName name) {
        ClassModel classModel = new ClassModel(name);
        classes.add(classModel);
        return classModel;
    }
    
	public ClassModel importClass(Class<?> clazz) {
		ClassModel classModel = new ClassModel(new ClassName(clazz.getName()));
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
		    classModel.setSuperClass(importType(superclass));
		}
		for (Class<?> iface : clazz.getInterfaces()) {
		    classModel.addInterface(importType(iface));
		}
		for (Method method : clazz.getDeclaredMethods()) {
			MethodModel methodModel = classModel.createMethod(method.getName());
			Class<?> returnType = method.getReturnType();
			if (returnType != Void.TYPE) {
			    methodModel.setReturnType(importType(returnType));
			}
			for (Class<?> paramClass : method.getParameterTypes()) {
			    methodModel.createArgument(importType(paramClass));
			}
			for (Class<?> exceptionType : method.getExceptionTypes()) {
			    methodModel.addException(importType(exceptionType));
			}
		}
		return classModel;
	}
	
	public MType importType(Class<?> clazz) {
		if (clazz.isPrimitive()) {
			return PrimitiveType.get(clazz);
		} if (clazz.isArray()) {
		    return new ArrayType(importType(clazz.getComponentType()));
		} else {
			return new MClassType(new ClassName(clazz.getName()));
		}
	}
	
	public void generate(File outputDir) throws IOException {
        for (ClassModel classModel : classes) {
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
