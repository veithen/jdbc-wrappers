package net.sf.jwrappers.generator;

import java.util.HashMap;
import java.util.Map;

import net.sf.jwrappers.generator.model.Imports;

public class PrimitiveType implements MType {
    private static final Map<Class<?>,PrimitiveType> map = new HashMap<Class<?>,PrimitiveType>();
    
    static {
        map.put(Integer.TYPE, new PrimitiveType("int"));
        map.put(Boolean.TYPE, new PrimitiveType("boolean"));
    }

    private final String name;
    
    private PrimitiveType(String name) {
        this.name = name;
    }
    
    public static PrimitiveType get(Class<?> clazz) {
        PrimitiveType result = map.get(clazz);
        if (result == null) {
            throw new IllegalArgumentException();
        } else {
            return result;
        }
    }

    public void collectImports(Imports imports) {
        // Nothing to do
    }

    public String toString(Imports imports) {
        return name;
    }
}
