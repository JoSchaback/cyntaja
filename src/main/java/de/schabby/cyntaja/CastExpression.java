package de.schabby.cyntaja;

import java.io.PrintWriter;

public class CastExpression implements Expression
{
    public final Variable castType;
    private Expression expression;

    public CastExpression(Variable castType, Expression expression)
    {
        this.castType = castType;
        this.expression = expression;
    }


    @Override
    public void toC(PrintWriter pw) {
        pw.print("("+castType.typeDeclaration()+") ");
        expression.toC(pw);
    }

    @Override
    public void visit(Visitor visitor)
    {
        expression = visitor.expression(expression);
        expression.visit(visitor);
    }

}
