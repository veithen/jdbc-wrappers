package net.sf.jwrappers.generator;

import net.sf.jwrappers.generator.model.ClassModel;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.ClassNameFormatter;
import net.sf.jwrappers.generator.model.Imports;

public class MClassType extends MType {
	private ClassName name;
	
	public MClassType(ClassName name) {
		this.name = name;
	}
	
	public MClassType(ClassModel classModel) {
	    name = classModel.getName();
	}
	
	public ClassName getName() {
        return name;
    }

    public ClassModel getMClass() {
		return null;
	}

    @Override
    public void collectImports(Imports imports) {
        imports.add(name);
    }

    @Override
    public String toString(ClassNameFormatter classNameFormatter) {
        return classNameFormatter.format(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MClassType && name.equals(((MClassType)obj).name);
    }
}
