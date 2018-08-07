package de.schabby.cyntaja;

import java.io.PrintWriter;

public class If implements Statement {

    private Expression condition;
    private Statement ifStatement = null;
    private Statement elseStatement = null;

    public If(Expression condition) {
        this.condition = condition;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.print("if(");
        condition.toC(pw);
        pw.print(") ");
        ifStatement.toC(pw, indentation);

        if( elseStatement != null )
        {
            pw.print(indentation+"else ");
            elseStatement.toC(pw, indentation);
        }
    }

    @Override
    public void visit(Visitor visitor)
    {
        condition = visitor.expression(condition);

        condition.visit(visitor);

        ifStatement = visitor.statement(ifStatement);
        ifStatement.visit(visitor);

        if( elseStatement != null )
            elseStatement = visitor.statement(elseStatement);
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getIfStatement() {
        return ifStatement;
    }

    public void setIfStatement(Statement ifStatement) {
        this.ifStatement = ifStatement;
    }

    public Statement getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(Statement elseStatement) {
        this.elseStatement = elseStatement;
    }
}
