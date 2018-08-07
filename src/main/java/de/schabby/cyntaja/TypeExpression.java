package de.schabby.cyntaja;

import java.io.PrintWriter;

public class TypeExpression implements Expression {

    public final Struct type;
    private boolean pointer = false;

    public TypeExpression(Struct type) {
        this.type = type;
    }

    @Override
    public void toC(PrintWriter pw)
    {
        pw.print(type.toC(false));
        if( pointer ) pw.print('*');
    }

    @Override
    public void visit(Visitor visitor) {

    }

    public boolean isPointer() {
        return pointer;
    }

    public void setPointer(boolean pointer) {
        this.pointer = pointer;
    }
}
