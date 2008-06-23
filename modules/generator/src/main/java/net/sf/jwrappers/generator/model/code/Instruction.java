package net.sf.jwrappers.generator.model.code;

import java.io.Serializable;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

public interface Instruction extends Serializable {
    void generate(CodeWriter out, Imports imports);
}
