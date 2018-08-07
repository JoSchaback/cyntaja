package de.schabby.cyntaja;

import java.io.PrintWriter;

public class UnaryOperator implements Expression, Statement {

    private Expression exp;
    private boolean isPrefix = true;
    private String operatorSymbol;

    public UnaryOperator(String operatorSymbol, Expression exp, boolean isPrefix) {
        this.exp = exp;
        this.isPrefix = isPrefix;
        this.operatorSymbol = operatorSymbol;
    }

    public boolean isPrefix() {
        return isPrefix;
    }

    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    @Override
    public void toC(PrintWriter pw) {
        this.toC(pw, "");
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.print(indentation);
        if(isPrefix)
            pw.print(operatorSymbol);

        exp.toC(pw);

        if(!isPrefix)
            pw.print(operatorSymbol);

    }

    @Override
    public void visit(Visitor visitor) {
        exp.visit(visitor);

        exp = visitor.expression(exp);
    }

    public Expression getExp() {
        return exp;
    }
}
