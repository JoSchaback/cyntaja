package de.schabby.cyntaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

public class NewObjectCreation implements Expression
{
    public final Struct struct;
    private FunctionCall constructorCall;
    private static final Logger log = LoggerFactory.getLogger(NewObjectCreation.class.getSimpleName());

    public NewObjectCreation(Struct struct, FunctionCall constructorCall)
    {
        this.struct = struct;
        this.constructorCall = constructorCall;
    }

    @Override
    public void toC(PrintWriter pw) {
        pw.print("/* new object "+struct.name+" */");
    }

    @Override
    public void visit(Visitor visitor)
    {
        constructorCall = (FunctionCall) visitor.expression(constructorCall);
        constructorCall.visit(visitor);
    }

    public FunctionCall getConstructorCall() {
        return constructorCall;
    }
}
