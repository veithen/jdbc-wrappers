package net.sf.jwrappers.generator.model.javadoc;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.MClassType;
import net.sf.jwrappers.generator.model.Argument;
import net.sf.jwrappers.generator.model.ClassName;
import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.model.MethodModel;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class JavadocModel implements Serializable {
    private static interface Piece {
        void generate(JavadocWriter out, Imports imports);
    }
    
    private static class TextPiece implements Piece {
        private final String text;

        public TextPiece(String text) {
            this.text = text;
        }

        public void generate(JavadocWriter out, Imports imports) {
            if (text.endsWith("\n")) {
                out.writeln(text.substring(0, text.length()-1));
            } else {
                out.write(text);
            }
        }
    }
    
    private static class ClassLink implements Piece {
        private final ClassName name;

        public ClassLink(ClassName name) {
            this.name = name;
        }

        public void generate(JavadocWriter out, Imports imports) {
            out.write("{@link ");
            out.write(new MClassType(name).toString(imports));
            out.write("}");
        }
    }
    
    private static class MethodLink implements Piece {
        private final MethodModel method;
        
        public MethodLink(MethodModel method) {
            this.method = method;
        }

        public void generate(JavadocWriter out, Imports imports) {
            out.write("{@link ");
            out.write(new MClassType(method.getClassModel()).toString(imports));
            out.write("#");
            out.write(method.getName());
            out.write("(");
            boolean first = true;
            for (Argument argument : method.getArguments()) {
                if (first) {
                    first = false;
                } else {
                    out.write(", ");
                }
                out.write(argument.getType().toString(imports));
            }
            out.write(")}");
        }
    }
    
    private static class Throws implements Piece {
        private final MClassType type;
        private final String description;
        
        public Throws(MClassType type, String description) {
            this.type = type;
            this.description = description;
        }

        public void generate(JavadocWriter out, Imports imports) {
            out.write("@throws ");
            out.write(type.toString(imports));
            out.write(" ");
            out.writeln(description);
        }
    }
    
    private final List<Piece> pieces = new LinkedList<Piece>();
    private final List<Throws> throwsTags = new LinkedList<Throws>();
    
    public void addText(String text) {
        pieces.add(new TextPiece(text));
    }
    
    public void addLink(ClassName className) {
        pieces.add(new ClassLink(className));
    }
    
    public void addLink(MethodModel method) {
        pieces.add(new MethodLink(method));
    }
    
    public void addThrows(MClassType type, String description) {
        throwsTags.add(new Throws(type, description));
    }
    
    public void generate(CodeWriter codeWriter, Imports imports) {
        List<Piece> allPieces = new LinkedList<Piece>();
        allPieces.addAll(pieces);
        allPieces.addAll(throwsTags);
        if (!allPieces.isEmpty()) {
            JavadocWriter out = new JavadocWriter(codeWriter);
            for (Piece piece : allPieces) {
                piece.generate(out, imports);
            }
            out.end();
        }
    }
}
