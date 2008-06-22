package net.sf.jwrappers.generator.model;

public interface ClassNameFormatter {
    ClassNameFormatter DEFAULT = new ClassNameFormatter() {
        public String format(ClassName className) {
            return className.toString();
        }
    };
    
    String format(ClassName className);
}
