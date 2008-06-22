package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class VariableDeclaration implements Instruction {
    private final MType type;
    private final String name;
    private final Expression expression;
    
    public VariableDeclaration(MType type, String name, Expression expression) {
        this.type = type;
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void generate(CodeWriter out, Imports imports) {
        out.write(type.toString(imports));
        out.write(" ");
        out.write(name);
        out.write(" = ");
        out.write(expression.toString(imports));
        out.writeln(";");
    }
}
