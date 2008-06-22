package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.Imports;

public interface MType {
    void collectImports(Imports imports);
    String toString(Imports imports);
}
