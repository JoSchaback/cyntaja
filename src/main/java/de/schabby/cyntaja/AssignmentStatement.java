package de.schabby.cyntaja;

import java.io.PrintWriter;

public class AssignmentStatement implements Statement
{
    private Expression target, source;
    private String operator;

    public AssignmentStatement(Expression target, Expression source, String operator)
    {
        this.target   = target;
        this.source   = source;
        this.operator = operator;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        target.toC(pw);
        pw.print(" "+operator+" ");
        source.toC(pw);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public void visit(Visitor visitor)
    {
        target = visitor.expression(target);
        source = visitor.expression(source);

        target.visit(visitor);
        source.visit(visitor);
    }
}
