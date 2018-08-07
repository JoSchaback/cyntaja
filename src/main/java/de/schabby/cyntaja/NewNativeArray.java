package de.schabby.cyntaja;

import java.io.PrintWriter;

public class NewNativeArray implements Expression
{
    public final Struct struct;
    private Expression init;

    public NewNativeArray(Struct struct, Expression initExp)
    {
        this.struct = struct;
        this.init   = initExp;
    }

    @Override
    public void toC(PrintWriter pw) {

    }

    @Override
    public void visit(Visitor visitor) {
        init = visitor.expression(init);
        init.visit(visitor);
    }

    public Expression getInit() {
        return init;
    }
}
