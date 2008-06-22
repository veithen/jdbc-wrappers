package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.CodeModel;
import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;
import net.sf.jwrappers.generator.writer.IndentCodeWriter;

public class IfStatement implements Instruction {
    private final Expression condition;
    private final CodeModel ifClause = new CodeModel();
    private CodeModel elseClause;
    
    public IfStatement(Expression condition) {
        this.condition = condition;
    }

    public CodeModel getIfClause() {
        return ifClause;
    }
    
    public CodeModel getElseClause() {
        if (elseClause == null) {
            elseClause = new CodeModel();
        }
        return elseClause;
    }

    public void generate(CodeWriter out, Imports imports) {
        out.write("if (");
        out.write(condition.toString(imports));
        out.writeln(") {");
        CodeWriter body = new IndentCodeWriter(out);
        ifClause.generate(body, imports);
        if (elseClause != null) {
            out.writeln("} else {");
            elseClause.generate(body, imports);
        }
        out.writeln("}");
    }
}
