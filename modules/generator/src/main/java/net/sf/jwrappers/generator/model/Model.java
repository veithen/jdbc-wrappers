package net.sf.jwrappers.generator.model;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.ArrayType;
import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.PrimitiveType;

public class Model {
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
}
