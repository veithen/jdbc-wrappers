package net.sf.jwrappers.generator.model.code;

import net.sf.jwrappers.generator.model.Attribute;
import net.sf.jwrappers.generator.model.Imports;

public class AttributeExpression implements LValue {
    private final Expression object;
    private final Attribute attribute;
    
    public AttributeExpression(Expression object, Attribute attribute) {
        this.object = object;
        this.attribute = attribute;
    }

    public String toString(Imports imports) {
        StringBuilder buffer = new StringBuilder();
        if (object == Expression.SELF) {
            // TODO
        } else {
            buffer.append(object.toString(imports));
            buffer.append('.');
        }
        buffer.append(attribute.getName());
        return buffer.toString();
    }
}
