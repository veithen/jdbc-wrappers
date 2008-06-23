package net.sf.jwrappers.generator.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.Access;
import net.sf.jwrappers.generator.MType;
import net.sf.jwrappers.generator.MethodSignature;
import net.sf.jwrappers.generator.model.javadoc.JavadocModel;
import net.sf.jwrappers.generator.writer.CodeWriter;
import net.sf.jwrappers.generator.writer.IndentCodeWriter;

public class MethodModel implements Serializable {
    private final ClassModel classModel;
    private final JavadocModel javadoc = new JavadocModel();
    private Access access = Access.PUBLIC;
    private boolean isFinal;
    private boolean isSynchonized;
    private MType returnType;
	private String name;
	private final List<Argument> arguments = new LinkedList<Argument>();
	private final List<MType> exceptions = new LinkedList<MType>();
	private final CodeModel code = new CodeModel();
	
	MethodModel(ClassModel classModel, String name) {
	    this.classModel = classModel;
		this.name = name;
	}
	
	public ClassModel getClassModel() {
        return classModel;
    }

    public JavadocModel getJavadoc() {
        return javadoc;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isSynchonized() {
        return isSynchonized;
    }

    public void setSynchonized(boolean isSynchonized) {
        this.isSynchonized = isSynchonized;
    }

    public MType getReturnType() {
        return returnType;
    }

    public void setReturnType(MType returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public Argument createArgument(String name, MType type) {
        Argument argument = new Argument(name, type);
	    arguments.add(argument);
	    return argument;
	}
    
    public Argument createArgument(MType type) {
        return createArgument("arg" + arguments.size(), type);
    }
	
    public List<MType> getExceptions() {
        return exceptions;
    }

    public void addException(MType exception) {
        exceptions.add(exception);
    }
    
    public CodeModel getCode() {
        return code;
    }
    
    public MethodSignature getSignature() {
        MType[] argumentTypes = new MType[arguments.size()];
        int i = 0;
        for (Argument argument : arguments) {
            argumentTypes[i++] = argument.getType();
        }
        return new MethodSignature(name, argumentTypes);
    }

    public void collectImports(Imports imports) {
        if (returnType != null) {
            returnType.collectImports(imports);
        }
        for (Argument argument : arguments) {
            argument.getType().collectImports(imports);
        }
        for (MType exception : exceptions) {
            exception.collectImports(imports);
        }
    }
    
	public void generate(CodeWriter out, Imports imports) {
	    javadoc.generate(out, imports);
        if (access.hasIdentifier()) {
            out.write(access.getIdentifier());
            out.write(" ");
        }
        if (isFinal) {
            out.write("final ");
        }
	    if (isSynchonized) {
	        out.write("synchronized ");
	    }
	    if (returnType == null) {
	        out.write("void");
	    } else {
	        out.write(returnType.toString(imports));
	    }
	    out.write(" ");
	    out.write(name);
	    out.write("(");
	    {
    	    boolean first = true;
    	    for (Argument argument : arguments) {
    	        if (first) {
    	            first = false;
    	        } else {
    	            out.write(", ");
    	        }
    	        out.write(argument.getType().toString(imports));
    	        out.write(" ");
    	        out.write(argument.getName());
    	    }
	    }
	    out.write(")");
	    if (!exceptions.isEmpty()) {
	        out.write(" throws ");
	        boolean first = true;
	        for (MType exception : exceptions) {
	            if (first) {
	                first = false;
	            } else {
	                out.write(", ");
	            }
	            out.write(exception.toString(imports));
	        }
	    }
	    out.writeln(" {");
	    code.generate(new IndentCodeWriter(out), imports);
	    out.writeln("}");
	}
}
