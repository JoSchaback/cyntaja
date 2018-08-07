package de.schabby.cyntaja;

import java.io.PrintWriter;

public class CharacterLiteral implements Expression {

    private char literal;

    public CharacterLiteral(char literal) {
        this.literal = literal;
    }

    @Override
    public void toC(PrintWriter pw) {
        pw.print(((int)literal));
        /*
        if( literal == '\n' )
            pw.print("'\\n'");
        else
            pw.print("'"+literal+"'");
            */
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.expression(this);
    }
}
