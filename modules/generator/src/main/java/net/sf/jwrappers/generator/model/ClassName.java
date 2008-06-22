package net.sf.jwrappers.generator.model;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class ClassName {
    private final String packageName;
    private final String unqualifiedName;
    
    public ClassName(String qualifiedName) {
        int idx = qualifiedName.lastIndexOf('.');
        packageName = qualifiedName.substring(0, idx);
        unqualifiedName = qualifiedName.substring(idx+1);
    }

    public ClassName(String packageName, String unqualifiedName) {
        this.packageName = packageName;
        this.unqualifiedName = unqualifiedName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getUnqualifiedName() {
        return unqualifiedName;
    }

    @Override
    public String toString() {
        return packageName + "." + unqualifiedName;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClassName &&
                packageName.equals(((ClassName)obj).packageName) &&
                unqualifiedName.equals(((ClassName)obj).unqualifiedName);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(packageName).append(unqualifiedName).toHashCode();
    }
}
