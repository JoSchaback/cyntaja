package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Parenthesis implements Expression {

    private Expression expression;

    public Parenthesis(Expression exp)
    {
        this.expression = exp;
    }

    @Override
    public void toC(PrintWriter pw) {
        pw.print("(");
        expression.toC(pw);
        pw.print(")");
    }

    @Override
    public void visit(Visitor visitor)
    {
        expression = visitor.expression(expression);

        expression.visit(visitor);
    }
}
