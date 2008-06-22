package net.sf.jwrappers.generator.model.javadoc;

import java.util.LinkedList;
import java.util.List;

import net.sf.jwrappers.generator.model.Imports;
import net.sf.jwrappers.generator.writer.CodeWriter;

public class JavadocModel {
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
    
    private final List<Piece> pieces = new LinkedList<Piece>();
    
    public void addText(String text) {
        pieces.add(new TextPiece(text));
    }
    
    public void generate(CodeWriter codeWriter, Imports imports) {
        JavadocWriter out = new JavadocWriter(codeWriter);
        for (Piece piece : pieces) {
            piece.generate(out, imports);
        }
        out.end();
    }
}
