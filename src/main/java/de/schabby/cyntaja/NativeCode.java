package de.schabby.cyntaja;

import java.io.PrintWriter;

public class NativeCode implements Statement {

    private String code;

    public NativeCode(String code) {
        this.code = code;
    }

    @Override
    public void toC(PrintWriter pw, String indentation) {
        pw.println("// native code start");
        pw.println(code);
        pw.println("// native code end");
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.statement(this);
    }
}
