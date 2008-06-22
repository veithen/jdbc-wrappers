package net.sf.jwrappers.generator.model;

import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.writer.CodeWriter;
import net.sf.jwrappers.generator.writer.IndentCodeWriter;

public class ClassModel {
	private ClassName name;
	private MType superClass;
	private final List<MType> interfaces = new LinkedList<MType>();
	private final List<Attribute> attributes = new LinkedList<Attribute>();
	private final List<MethodModel> methods = new LinkedList<MethodModel>();
	
	ClassModel(ClassName name) {
		this.name = name;
	}
	
	public ClassName getName() {
        return name;
    }

    public void setName(ClassName name) {
        this.name = name;
    }

    public MType getSuperClass() {
        return superClass;
    }

    public void setSuperClass(MType superClass) {
        this.superClass = superClass;
    }

    public List<MType> getInterfaces() {
        return interfaces;
    }
    
    public void addInterface(MType iface) {
        interfaces.add(iface);
    }

    public Attribute createAttribute(String name, MType type) {
	    Attribute attribute = new Attribute(name, type);
	    attributes.add(attribute);
	    return attribute;
	}
	
	public MethodModel createMethod(String name) {
	    MethodModel method = new MethodModel(this, name);
	    methods.add(method);
	    return method;
	}
	
	public MethodModel overrideMethod(MethodModel baseMethod) {
	    MethodModel method = createMethod(baseMethod.getName());
	    method.setReturnType(baseMethod.getReturnType());
	    for (Argument argument : baseMethod.getArguments()) {
	        method.createArgument(argument.getName(), argument.getType());
	    }
	    for (MType exception : baseMethod.getExceptions()) {
	        method.addException(exception);
	    }
	    return method;
	}
	
	public List<MethodModel> getMethods() {
	    return methods;
	}
	
	public void collectImports(Imports imports) {
	    if (superClass != null) {
	        superClass.collectImports(imports);
	    }
	    for (MType iface : interfaces) {
	        iface.collectImports(imports);
	    }
	    for (Attribute attribute : attributes) {
	        attribute.getType().collectImports(imports);
	    }
	    for (MethodModel method : methods) {
	        method.collectImports(imports);
	    }
	}
	
    public void generate(CodeWriter out) {
        Imports imports = new Imports(name.getPackageName());
        collectImports(imports);
        out.write("package ");
        out.write(name.getPackageName());
        out.writeln(";");
        out.writeln();
        imports.generate(out);
        out.write("public class ");
        out.write(name.getUnqualifiedName());
        if (superClass != null) {
            out.write(" extends ");
            out.write(superClass.toString(imports));
        }
        if (!interfaces.isEmpty()) {
            out.write(" implements ");
            boolean first = true;
            for (MType iface : interfaces) {
                if (first) {
                    first = false;
                } else {
                    out.write(", ");
                }
                out.write(iface.toString(imports));
            }
        }
        out.writeln(" {");
        CodeWriter body = new IndentCodeWriter(out);
        boolean addEmptyLine = false;
        if (!attributes.isEmpty()) {
            for (Attribute attribute : attributes) {
                attribute.generate(body, imports);
            }
            addEmptyLine = true;
        }
        for (MethodModel method : methods) {
            if (addEmptyLine) {
                body.writeln();
            }
            method.generate(body, imports);
            addEmptyLine = true;
        }
        out.writeln("}");
    }
}
