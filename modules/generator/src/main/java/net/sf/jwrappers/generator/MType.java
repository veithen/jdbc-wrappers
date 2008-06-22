package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public abstract class MType {
    public abstract void collectImports(Imports imports);
    
    public abstract String toString(ClassNameFormatter classNameFormatter);

    @Override
    public final String toString() {
        return toString(ClassNameFormatter.DEFAULT);
    }
}
