package net.sf.jwrappers.generator.model;

import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class Attribute {
    private String name;
    private MType type;
    
    Attribute(String name, MType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public MType getType() {
        return type;
    }
    
    public void setType(MType type) {
        this.type = type;
    }

    public void generate(CodeWriter out, Imports imports) {
        out.write(type.toString(imports));
        out.write(" ");
        out.write(name);
        out.writeln(";");
    }
}
