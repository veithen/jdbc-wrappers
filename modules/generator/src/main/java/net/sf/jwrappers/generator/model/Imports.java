package net.sf.jwrappers.generator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import net.sf.jwrappers.generator.writer.CodeWriter;

public class Imports {
    private static final Pattern[] importGroups = {
        Pattern.compile("java\\..*"),
        Pattern.compile("javax\\..*"),
    };
    
    private final String packageName;
    private final Set<ClassName> classNames = new HashSet<ClassName>();
    
    public Imports(String packageName) {
        this.packageName = packageName;
    }

    public void add(ClassName className) {
        if (requiresQualification(className)) {
            classNames.add(className);
        }
    }
    
    private boolean requiresQualification(ClassName className) {
        String packageName = className.getPackageName();
        return !packageName.equals("java.lang") && !packageName.equals(this.packageName);
    }
    
    public void generate(CodeWriter out) {
        List<SortedSet<String>> groups = new ArrayList<SortedSet<String>>(importGroups.length+1);
        for (int i=0; i<importGroups.length+1; i++) {
            groups.add(new TreeSet<String>());
        }
        for (ClassName className : classNames) {
            String packageName = className.getPackageName();
            int groupIndex;
            for (groupIndex=0; groupIndex<importGroups.length; groupIndex++) {
                if (importGroups[groupIndex].matcher(packageName).matches()) {
                    break;
                }
            }
            groups.get(groupIndex).add(className.toString());
        }
        for (SortedSet<String> group : groups) {
            if (!group.isEmpty()) {
                for (String name : group) {
                    out.write("import ");
                    out.write(name);
                    out.writeln(";");
                }
                out.writeln();
            }
        }
    }
    
    public String format(ClassName className) {
        return classNames.contains(className) || !requiresQualification(className)
                    ? className.getUnqualifiedName() : className.toString();
    }
}
