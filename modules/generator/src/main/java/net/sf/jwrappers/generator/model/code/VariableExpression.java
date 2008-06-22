package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;

public class VariableExpression implements LValue {
    private final VariableDeclaration declaration;

    public VariableExpression(VariableDeclaration declaration) {
        this.declaration = declaration;
    }

    public String toString(Imports imports) {
        return declaration.getName();
    }
}
