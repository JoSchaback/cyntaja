package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Identifier implements Expression, Statement, Chainable
{
    public final String typeIdentifier;
    public String identifier;
    private Expression arrayAccessExp = null;
    private Chainable next = null, previous = null;
    private boolean isMethodParameter = false, isClassField = false, isArray = false;

    public Identifier(Variable variable)
    {
        this(variable, null);
    }

    public Identifier(Variable variable, Chainable next)
    {
        this(variable.typeIdentifier, variable.name, next);

        isClassField      = variable.isClassField();
        isMethodParameter = variable.isFunctionParameter();
        isArray           = variable.isHeapArray();
    }

    public Identifier(String type, String name, Chainable next)
    {
        this.typeIdentifier = type;
        this.identifier = name;
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

    public void setNext(Chainable next) {
        this.next = next;
    }

    @Override
    public void toC(PrintWriter pw) {
        pw.print(identifier);

        if( arrayAccessExp != null)
        {
            pw.print("[");
            arrayAccessExp.toC(pw);
            pw.print("]");
        }

        if( next != null )
        {
            pw.print("->");

            next.toC(pw);
        }

    }

    public boolean isMethodParameter() {
        return isMethodParameter;
    }

    public void setMethodParameter(boolean methodParameter) {
        isMethodParameter = methodParameter;
    }

    public boolean isClassField() {
        return isClassField;
    }

    public void setClassField(boolean classField) {
        isClassField = classField;
    }

    public boolean isArray() {
        return isArray;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        toC(pw);
    }

    @Override
    public void visit(Visitor visitor)
    {
        if( arrayAccessExp != null ) {
            arrayAccessExp = visitor.expression(arrayAccessExp);
            arrayAccessExp.visit(visitor);
        }

        if( next != null ) {
            next = visitor.chainable(next);
            next.visit(visitor);
        }
    }


    public void setArray(boolean array) {
        isArray = array;
    }

    public Expression getArrayAccessExp() {
        return arrayAccessExp;
    }

    public void setArrayAccessExp(Expression arrayAccessExp) {
        this.arrayAccessExp = arrayAccessExp;
    }

    public void setPrevious(Chainable previous) {
        this.previous = previous;
    }
}
