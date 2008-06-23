package net.sf.jwrappers.generator.model;

import java.io.Serializable;

import net.sf.jwrappers.generator.Access;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class Attribute implements Serializable {
    private Access access = Access.PRIVATE;
    private String name;
    private MType type;
    
    Attribute(String name, MType type) {
        this.name = name;
        this.type = type;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
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
        if (access.hasIdentifier()) {
            out.write(access.getIdentifier());
            out.write(" ");
        }
        out.write(type.toString(imports));
        out.write(" ");
        out.write(name);
        out.writeln(";");
    }
}
