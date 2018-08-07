package de.schabby.cyntaja;

import java.io.PrintWriter;

public interface Statement
{

    void toC(PrintWriter pw, String indentation);

    void visit(Visitor visitor);
}
