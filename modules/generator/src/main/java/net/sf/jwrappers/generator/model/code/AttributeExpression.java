package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.Imports;

public class AttributeExpression implements Expression {
    private final Expression object;
    private final Attribute attribute;
    
    public AttributeExpression(Expression object, Attribute attribute) {
        this.object = object;
        this.attribute = attribute;
    }

    public String toString(Imports imports) {
        return object.toString(imports) + "." + attribute.getName();
    }
}
