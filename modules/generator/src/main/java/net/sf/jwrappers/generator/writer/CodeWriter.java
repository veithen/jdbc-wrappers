package net.sf.jwrappers.generator.writer;

public abstract class CodeWriter {
    public abstract void write(String s);
    public abstract void writeln();
    
    public void writeln(String s) {
        write(s);
        writeln();
    }
}
