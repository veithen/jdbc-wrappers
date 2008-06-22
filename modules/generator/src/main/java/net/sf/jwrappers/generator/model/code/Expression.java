package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Imports;

public interface Expression {
    Expression SELF = new Expression() {
        public String toString(Imports imports) {
            return "this";
        }
    };
    
    Expression NULL = new Expression() {
        public String toString(Imports imports) {
            return "null";
        }
    };
    
    String toString(Imports imports);
}
