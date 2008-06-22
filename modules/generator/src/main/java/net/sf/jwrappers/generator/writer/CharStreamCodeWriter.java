package net.sf.jwrappers.generator.writer;

import java.io.PrintWriter;
import java.io.Writer;

public class CharStreamCodeWriter extends CodeWriter {
    private final PrintWriter out;

    public CharStreamCodeWriter(Writer out) {
        this.out = new PrintWriter(out);
    }

    @Override
    public void write(String s) {
        out.print(s);
    }

    @Override
    public void writeln() {
        out.println();
    }

    public void flush() {
        out.flush();
    }
}
