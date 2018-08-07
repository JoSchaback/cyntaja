package de.schabby.cyntaja;

import java.io.PrintWriter;

public class ReturnStatement implements Statement {

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    private Expression exp = null;

    public ReturnStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public void toC(PrintWriter pw, String indentation)
    {
        pw.print("return ");
        exp.toC(pw);
    }

    @Override
    public void visit(Visitor visitor)
    {
        if( exp != null ) {
            exp = visitor.expression(exp);

            exp.visit(visitor);
        }
    }


}
