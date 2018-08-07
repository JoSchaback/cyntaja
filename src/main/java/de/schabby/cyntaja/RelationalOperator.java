package de.schabby.cyntaja;

import java.io.PrintWriter;

public class RelationalOperator implements Expression {

    public final String operator;
    private Expression left;
    private Expression right;

    public RelationalOperator(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
    @Override
    public void toC(PrintWriter pw)
    {
        left.toC(pw);
        pw.print(" "+operator+" ");
        right.toC(pw);
    }

    @Override
    public void visit(Visitor visitor)
    {
        left  = visitor.expression(left);
        right = visitor.expression(right);

        left.visit(visitor);
        right.visit(visitor);
    }
}
