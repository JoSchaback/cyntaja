package de.schabby.cyntaja;

import java.io.PrintWriter;

public class VariableDeclarationStatement implements Statement
{
    public final Variable variable;
    private Expression assignementExpression = null;

    public VariableDeclarationStatement(Variable var, Expression expression)
    {
        this.variable = var;
        this.assignementExpression = expression;
    }

    @Override
    public void toC(PrintWriter pw, String indentation)
    {
        pw.print(variable.fullDeclaration());

        if( assignementExpression != null )
        {
            pw.print(" = ");
            assignementExpression.toC(pw);
        }
    }

    public void setAssignementExpression(Expression assignementExpression) {
        this.assignementExpression = assignementExpression;
    }

    @Override
    public void visit(Visitor visitor)
    {
        //visitor.statement(this);

        if( assignementExpression != null ) {
            assignementExpression = visitor.expression(assignementExpression);
            assignementExpression.visit(visitor);
        }
    }

}
