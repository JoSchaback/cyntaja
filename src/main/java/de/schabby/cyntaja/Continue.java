package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Continue implements Statement {

    private String label;

    public Continue(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.print("continue");

        if( label != null )
            pw.print(" "+label);
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.statement(this);
    }
}
