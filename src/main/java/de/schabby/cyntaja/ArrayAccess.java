package de.schabby.cyntaja;

import java.io.PrintWriter;

/**
 * Provides Array access with brackets, such as a[i+1]
 */
public class ArrayAccess implements Expression {

    private Variable variable;

    /**
     * the part beetween the brackets
     */
    private Expression indexExpression;

    public ArrayAccess(Variable variable)
    {
        this.variable = variable;
    }

    public ArrayAccess(Variable variable, Expression expression)
    {
        this.variable = variable;
        this.indexExpression = expression;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Expression getIndexExpression() {
        return indexExpression;
    }

    public void setIndexExpression(Expression indexExpression) {
        this.indexExpression = indexExpression;
    }

    @Override
    public void toC(PrintWriter pw) {
        pw.print(variable.identifier);
        pw.print("[");
        indexExpression.toC(pw);
        pw.print("]");
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.expression(indexExpression);
        indexExpression.visit(visitor);
    }
}
