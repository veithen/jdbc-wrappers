package net.sf.jwrappers.generator;

import java.util.HashMap;
import java.util.Map;

import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public class PrimitiveType extends MType {
    private static final Map<Class<?>,PrimitiveType> primitiveTypeMap = new HashMap<Class<?>,PrimitiveType>();
    
    private final String name;
    
    private PrimitiveType(String name) {
        this.name = name;
    }
    
    public static PrimitiveType get(Class<?> clazz) {
        PrimitiveType primitiveType = primitiveTypeMap.get(clazz);
        if (primitiveType == null) {
            primitiveType = new PrimitiveType(clazz.getName());
            primitiveTypeMap.put(clazz, primitiveType);
        }
        return primitiveType;
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
