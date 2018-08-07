package de.schabby.cyntaja;

import java.io.PrintWriter;

/**
 * Implements the reference operator ampersand (&).
 *
 * TODO isnt that more of a specific Identifier???
 */
public class ToPointer implements Expression {

    private Variable variable;

    public ToPointer(Variable variable) {
        this.variable = variable;
    }

    @Override
    public void toC(PrintWriter pw) {
        pw.print("&"+variable.name);
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.expression(this);
    }
}
