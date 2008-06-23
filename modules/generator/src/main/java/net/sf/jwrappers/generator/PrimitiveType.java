package net.sf.jwrappers.generator;

import java.util.HashMap;
import java.util.Map;

import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public class PrimitiveType extends MType {
    private static final String[] types = { "boolean", "byte", "char", "double", "float", "int", "long", "short" };
    
    private static final Map<String,PrimitiveType> primitiveTypeFromString = new HashMap<String,PrimitiveType>();
    
    static {
        for (String type : types) {
            primitiveTypeFromString.put(type, new PrimitiveType(type));
        }
    }
    
    private final String name;
    
    private PrimitiveType(String name) {
        this.name = name;
    }
    
    public static PrimitiveType get(Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            throw new IllegalArgumentException();
        }
        return get(clazz.getName());
    }
    
    public static PrimitiveType get(String type) {
        PrimitiveType result = primitiveTypeFromString.get(type);
        if (result == null) {
            throw new IllegalArgumentException("'" + type + "' is not a primitive type");
        }
        return result;
    }

    @Override
    public void collectImports(Imports imports) {
        // Nothing to do
    }

    @Override
    public String toString(ClassNameFormatter classNameFormatter) {
        return name;
    }
}
