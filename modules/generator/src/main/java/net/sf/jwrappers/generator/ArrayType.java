package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public class ArrayType extends MType {
    private final MType componentType;

    public ArrayType(MType componentType) {
        this.componentType = componentType;
    }

    @Override
    public void collectImports(Imports imports) {
        componentType.collectImports(imports);
    }

    @Override
    public String toString(ClassNameFormatter classNameFormatter) {
        return componentType.toString(classNameFormatter) + "[]";
    }
}
