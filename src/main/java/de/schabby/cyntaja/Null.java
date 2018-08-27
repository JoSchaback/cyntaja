package de.schabby.cyntaja;

import java.io.PrintWriter;

/**
 * Implementation of a Null Statement.
 */
public class Null implements Statement, Expression {

    @Override
    public void toC(PrintWriter pw, String indentation) {
        // it is in fact empty
    }

    @Override
    public void toC(PrintWriter pw) {
        // it is in fact empty
    }

    @Override
    public void visit(Visitor visitor) {
        // do not do anything
    }

    /**
     * Instance variable as Null is state-less.
     */
    public static final Null INSTANCE = new Null();
}
