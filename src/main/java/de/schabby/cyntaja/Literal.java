package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Literal implements Expression
{
    public String literal;
    public final boolean withQuotes;

    public Literal(String literal)
    {
        this(literal, false);
    }

    public Literal(String literal, boolean withQuotes)
    {
        this.literal = literal;
        this.withQuotes = withQuotes;
    }

    @Override
    public void toC(PrintWriter pw)
    {
        // if( literal.contains("b") )
        String v = literal.replace("b", "");

        if( withQuotes )
            pw.print("\""+v+"\"");
        else
            pw.print(v);
    }

    @Override
    public void visit(Visitor visitor)
    {

    }

}
