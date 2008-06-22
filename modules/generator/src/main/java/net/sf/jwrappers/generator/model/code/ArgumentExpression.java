package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Argument;
import net.sf.jwrappers.generator.model.Imports;

public class ArgumentExpression implements Expression {
    private final Argument argument;

    public ArgumentExpression(Argument argument) {
        this.argument = argument;
    }

    public String toString(Imports imports) {
        return argument.getName();
    }
}
