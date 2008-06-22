package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.Imports;

public class MClassType implements MType {
	private ClassName name;
	
	public MClassType(ClassName name) {
		this.name = name;
	}
	
	public MClassType(ClassModel classModel) {
	    name = classModel.getName();
	}
	
	public ClassModel getMClass() {
		return null;
	}

    public void collectImports(Imports imports) {
        imports.add(name);
    }

    public String toString(Imports imports) {
        return imports.format(name);
    }
}
