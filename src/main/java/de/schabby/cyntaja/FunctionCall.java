package de.schabby.cyntaja;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FunctionCall implements Expression, Statement, Chainable
{
    private String name = null;
    public final List<Expression> params = new ArrayList<>();
    private Chainable next = null, previous = null;
    private Function function;

    public FunctionCall(String name) {
        this.name = name;
    }

    public FunctionCall(Function func, Expression... expressions)
    {
        this((String)null, expressions);
        this.function = func;
    }

    // TODO I think at some point we should create real CFuntions for each stdlib function
    // such that we can link all FunctionCallStatements to CFunctions
    public FunctionCall(String name, Expression... expressions)
    {
        this(name);

        for( Expression exp: expressions)
        {
            params.add(exp);
        }
    }

    public String getName() {
        if( function != null ) return function.getName();
        else return name;
    }

    @Override
    public void toC(PrintWriter pw)
    {
        pw.print(getName()+"(");

        for(int i = 0; i < params.size(); i++)
        {
            Expression exp = params.get(i);
            exp.toC(pw);
            if( i < params.size() - 1 )
                pw.print(", ");
        }
        pw.print(")");
    }

    public void setNext(Chainable next) {
        this.next = next;
    }

    @Override
    public Chainable getNext() {
        return next;
    }

    @Override
    public Chainable getPrevious() {
        return previous;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        toC(pw);
    }

    @Override
    public void visit(Visitor visitor)
    {
        for(int i = 0; i < params.size(); i++ )
        {
            Expression oldExp = params.remove(i);
            Expression newExp = visitor.expression(oldExp);
            params.add(i, newExp);
            newExp.visit(visitor);
        }
    }

    public void setPrevious(Chainable previous) {
        this.previous = previous;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
