package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

public interface Instruction {
    void generate(CodeWriter out, Imports imports);
}
