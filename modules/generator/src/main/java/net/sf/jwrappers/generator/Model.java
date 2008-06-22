package net.sf.jwrappers.generator;

import java.lang.reflect.Method;

import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.MethodModel;

public class Model {
	public ClassModel importClass(Class<?> clazz) {
		ClassModel classModel = new ClassModel(new ClassName(clazz.getName()));
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
		} else {
			return new MClassType(new ClassName(clazz.getName()));
		}
	}
}
