package de.schabby.cyntaja;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class StaticStructInitializer implements Expression {

    private Struct struct;
    private Map<Field, Expression> mapping = new HashMap<>();

    public StaticStructInitializer(Struct struct) {
        this.struct = struct;
    }

    @Override
    public void toC(PrintWriter pw)
    {
        pw.print("{");
        for( int i = 0; i < struct.fields.size(); i++ ) {
            Field field = struct.fields.get(i);
            pw.print("."+field.getVariable().identifier+"=");
            Expression exp = mapping.get(field); // TODO we could check here that it is a literal, otherwise the C compiler would not compile it
            if( exp == null ) throw new RuntimeException("could not find mapping for field '"+field.getVariable().identifier+"', but all fields need to be mapped onto a literal expression");
            exp.toC(pw);
            if( i+1 < struct.fields.size() )
                pw.print(", ");
        }
        pw.print("}");
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.expression(this);
        for( Expression exp: mapping.values() )
            exp.visit(visitor);
    }

    public Struct getStruct() {
        return struct;
    }

    public Map<Field, Expression> getMapping() {
        return mapping;
    }
}
