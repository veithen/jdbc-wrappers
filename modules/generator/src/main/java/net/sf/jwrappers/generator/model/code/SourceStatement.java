package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

// TODO: quick and dirty hack; to be removed
public class SourceStatement implements Instruction {
    private final String source;

    public SourceStatement(String source) {
        this.source = source;
    }

    public void generate(CodeWriter out, Imports imports) {
        out.write(source);
        out.writeln(";");
    }
}
