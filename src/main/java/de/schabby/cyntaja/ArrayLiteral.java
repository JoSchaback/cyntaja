package de.schabby.cyntaja;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * C Array Literal
 */
public class ArrayLiteral implements Expression {

    private List<Expression> elements = new ArrayList<>();
    private Struct type;

    @Override
    public void toC(PrintWriter pw)
    {
        pw.print("{");
        for( int i =0; i < elements.size();i++ )
        {
            elements.get(i).toC(pw);

            if( i < elements.size()-1 )
                pw.print(", ");
        }
        pw.print("}");
    }

    @Override
    public void visit(Visitor visitor)
    {
        for( int i =0; i < elements.size();i++ )
        {
            Expression exp = elements.remove(i);
            exp = visitor.expression(exp);
            elements.add(i, exp);
            exp.visit(visitor);
        }

    }

    public List<Expression> getElements() {
        return elements;
    }

    public void setType(Struct type) {
        this.type = type;
    }

    public Struct getType() {
        return type;
    }
}
