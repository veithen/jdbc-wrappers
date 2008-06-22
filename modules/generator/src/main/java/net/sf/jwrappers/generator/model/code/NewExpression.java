package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.model.Imports;

public class NewExpression implements Expression {
    private final MType type;

    public NewExpression(MType type) {
        this.type = type;
    }

    public String toString(Imports imports) {
        return "new " + type.toString(imports) + "()";
    }
}
