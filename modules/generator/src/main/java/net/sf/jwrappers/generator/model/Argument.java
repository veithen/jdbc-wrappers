package net.sf.jwrappers.generator.model;

import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.model.code.Expression;

public class Argument implements Expression {
	private String name;
	private MType type;
	
	Argument(String name, MType type) {
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

    public String toString(Imports imports) {
        return name;
    }
}
