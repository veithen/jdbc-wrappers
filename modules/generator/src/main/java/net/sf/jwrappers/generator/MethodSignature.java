package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public class MethodSignature {
    private final String name;
    private final MType[] argumentTypes;
    
    public MethodSignature(String name, MType[] argumentTypes) {
        this.name = name;
        this.argumentTypes = argumentTypes;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(name);
        buffer.append('(');
        boolean first = true;
        for (MType type : argumentTypes) {
            if (first) {
                first = false;
            } else {
                buffer.append(", ");
            }
            buffer.append(type.toString(ClassNameFormatter.DEFAULT));
        }
        buffer.append(')');
        return buffer.toString();
    }
}
