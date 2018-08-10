package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Cast implements Expression
{
    public final Type castType;
    private Expression expression;

    public Cast(Type castType, Expression expression)
    {
        this.castType = castType;
        this.expression = expression;
    }


    @Override
    public void toC(PrintWriter pw)
    {
        pw.print("("+castType.toC()+") ");
        expression.toC(pw);
    }

    @Override
    public void visit(Visitor visitor)
    {
        expression = visitor.expression(expression);
        expression.visit(visitor);
    }

}
