package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class ReturnInstruction implements Instruction {
    private final Expression expression;

    public ReturnInstruction(Expression expression) {
        this.expression = expression;
    }

    public void generate(CodeWriter out, Imports imports) {
        out.write("return ");
        out.write(expression.toString(imports));
        out.writeln(";");
    }
}
