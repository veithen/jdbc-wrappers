package net.sf.jwrappers.generator;

public class MethodSignature implements Comparable<MethodSignature> {
    private final String name;
    private final MType[] argumentTypes;
    
    public MethodSignature(String name, MType[] argumentTypes) {
        this.name = name;
        this.argumentTypes = argumentTypes;
    }

    public int compareTo(MethodSignature o) {
        int c = name.compareToIgnoreCase(o.name);
        if (c != 0) {
            return c;
        }
        c = argumentTypes.length - o.argumentTypes.length;
        if (c != 0) {
            return c;
        }
        return 0; // TODO
    }
}
