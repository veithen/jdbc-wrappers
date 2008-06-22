package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;

public interface Expression {
    Expression SELF = new Expression() {
        public String toString(Imports imports) {
            return "this";
        }
    };
    
    String toString(Imports imports);
}
