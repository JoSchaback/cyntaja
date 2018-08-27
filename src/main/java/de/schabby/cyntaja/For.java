package de.schabby.cyntaja;


import java.io.PrintWriter;

public class For implements Statement {

    private Statement initStmnt;
    private Expression conditional;
    private Statement increment;
    private Statement execStatement = Null.INSTANCE;

    public For() {
        this(Null.INSTANCE, Null.INSTANCE, Null.INSTANCE);
    }

    public For(Statement initStmnt, Expression conditional, Statement increment) {
        this.initStmnt = initStmnt;
        this.conditional = conditional;
        this.increment = increment;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.print("for(");
        initStmnt.toC(pw, "");
        pw.print("; ");
        conditional.toC(pw);
        pw.print("; ");
        increment.toC(pw, "");
        pw.print(") ");
        if( execStatement instanceof Block)
            execStatement.toC(pw, indentation);
        else {
            pw.print("\n");
            pw.print(indentation+"\t");
            execStatement.toC(pw, indentation+"\t");
        }
    }

    @Override
    public void visit(Visitor visitor) {
        initStmnt = visitor.statement( initStmnt);
        initStmnt.visit(visitor);

        conditional = visitor.expression(conditional);
        conditional.visit(visitor);

        increment = visitor.statement(increment);
        increment.visit(visitor);

        execStatement = visitor.statement(execStatement);
        execStatement.visit(visitor);
    }

    public Statement getInitStmnt() {
        return initStmnt;
    }
    public void setInitStmnt(Statement initStmnt) {
        this.initStmnt = initStmnt;
    }
    public Expression getConditional() {
        return conditional;
    }
    public void setConditional(Expression conditional) {
        this.conditional = conditional;
    }
    public Statement getIncrement() {
        return increment;
    }
    public void setIncrement(Statement increment) {
        this.increment = increment;
    }
    public Statement getExecStatement() { return execStatement; }
    public void setExecStatement(Statement execStatement) { this.execStatement = execStatement; }


}
