package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.Imports;

public class ArrayType implements MType {
    private final MType componentType;

    public ArrayType(MType componentType) {
        this.componentType = componentType;
    }

    public void collectImports(Imports imports) {
        componentType.collectImports(imports);
    }

    public String toString(Imports imports) {
        return componentType.toString(imports) + "[]";
    }
}
