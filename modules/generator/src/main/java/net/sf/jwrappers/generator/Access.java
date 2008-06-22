package net.sf.jwrappers.generator;

public enum Access {
    PUBLIC("public"), PRIVATE("private"), PROTECTED("protected"), PACKAGE(null);
    
    private final String identifier;
    
    Access(String identifier) {
        this.identifier = identifier;
    }

    public boolean hasIdentifier() {
        return identifier != null;
    }
    
    public String getIdentifier() {
        return identifier;
    }
}
