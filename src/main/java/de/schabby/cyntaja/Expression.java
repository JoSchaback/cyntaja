package de.schabby.cyntaja;

import java.io.PrintWriter;

public interface Expression
{

    void toC(PrintWriter pw);
    void visit(Visitor visitor);
}
