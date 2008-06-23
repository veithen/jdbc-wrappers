package net.sf.jwrappers.generator.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.model.code.Expression;
import net.sf.jwrappers.generator.model.code.Instruction;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class CodeModel implements Serializable {
    private final List<Instruction> instructions = new LinkedList<Instruction>();
    
    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }
    
    public void addInstruction(final Expression expression) {
        instructions.add(new Instruction() {
            public void generate(CodeWriter out, Imports imports) {
                out.write(expression.toString(imports));
                out.writeln(";");
            }
        });
    }
    
    public void generate(CodeWriter out, Imports imports) {
        for (Instruction instruction : instructions) {
            instruction.generate(out, imports);
        }
    }
}
