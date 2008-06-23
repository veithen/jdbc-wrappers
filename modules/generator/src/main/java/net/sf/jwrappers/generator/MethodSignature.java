package net.sf.jwrappers.generator;

import java.util.Arrays;

import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.jwrappers.generator.model.ClassNameFormatter;

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

    @Override
    public boolean equals(Object _obj) {
        if (_obj instanceof MethodSignature) {
            MethodSignature obj = (MethodSignature)_obj;
            return name.equals(obj.name) && Arrays.equals(argumentTypes, obj.argumentTypes);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(argumentTypes).toHashCode();
    }
}
