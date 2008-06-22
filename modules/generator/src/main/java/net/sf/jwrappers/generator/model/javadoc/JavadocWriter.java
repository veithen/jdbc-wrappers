package net.sf.jwrappers.generator.model.javadoc;

import net.sf.jwrappers.generator.writer.CodeWriter;

public class JavadocWriter extends CodeWriter {
    private final CodeWriter parent;
    private boolean startOfLine = true;

    public JavadocWriter(CodeWriter parent) {
        this.parent = parent;
        parent.writeln("/**");
    }

    @Override
    public void write(String s) {
        if (startOfLine) {
            parent.write(" * ");
            parent.write(s);
            startOfLine = false;
        } else {
            parent.write(s);
        }
    }

    @Override
    public void writeln() {
        parent.writeln();
        startOfLine = true;
    }
    
    public void end() {
        if (!startOfLine) {
            parent.writeln();
        }
        parent.writeln(" */");
    }
}
