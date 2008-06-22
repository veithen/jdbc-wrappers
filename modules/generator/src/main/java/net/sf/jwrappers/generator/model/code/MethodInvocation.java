package net.sf.jwrappers.generator.model.code;

import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.model.MethodModel;

public class MethodInvocation implements Expression {
    private final Expression object;
    private final MethodModel method;
    private final List<Expression> arguments = new LinkedList<Expression>();
    
    public MethodInvocation(Expression object, MethodModel method) {
        this.object = object;
        this.method = method;
    }
    
    public void addArgument(Expression expression) {
        arguments.add(expression);
    }

    public String toString(Imports imports) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(object.toString(imports));
        buffer.append('.');
        buffer.append(method.getName());
        buffer.append('(');
        boolean first = true;
        for (Expression argument : arguments) {
            if (first) {
                first = false;
            } else {
                buffer.append(", ");
            }
            buffer.append(argument.toString(imports));
        }
        buffer.append(')');
        return buffer.toString();
    }
}
