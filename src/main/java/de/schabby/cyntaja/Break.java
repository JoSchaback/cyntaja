package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Break implements Statement {

    private String label;

    public Break(String label) {
        this.label = label;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.print("break");
        if( label != null )
            pw.print(" "+label);
        //pw.println(";");
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.statement(this);
    }
}
