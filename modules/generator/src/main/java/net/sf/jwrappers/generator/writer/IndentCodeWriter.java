package net.sf.jwrappers.generator.writer;

public class IndentCodeWriter extends CodeWriter {
    private final CodeWriter parent;
    private boolean startOfLine = true;

    public IndentCodeWriter(CodeWriter parent) {
        this.parent = parent;
    }

    @Override
    public void write(String s) {
        if (startOfLine) {
            parent.write("    ");
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
}
