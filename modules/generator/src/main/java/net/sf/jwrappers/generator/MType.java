package net.sf.jwrappers.generator;

import java.io.Serializable;

import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public abstract class MType implements Serializable {
    public abstract void collectImports(Imports imports);
    
    public abstract String toString(ClassNameFormatter classNameFormatter);

    @Override
    public final String toString() {
        return toString(ClassNameFormatter.DEFAULT);
    }
}
