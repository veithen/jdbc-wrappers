package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class Assignment implements Instruction {
    private final LValue lvalue;
    private final Expression expression;
    
    public Assignment(LValue lvalue, Expression expression) {
        this.lvalue = lvalue;
        this.expression = expression;
    }

    public void generate(CodeWriter out, Imports imports) {
        out.write(lvalue.toString(imports));
        out.write(" = ");
        out.write(expression.toString(imports));
        out.writeln(";");
    }
}
