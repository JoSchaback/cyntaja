package de.schabby.cyntaja;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A Struct in the C programming language.
 */
public class Struct {

    public String name;
    public final List<Field> fields = new ArrayList<>();
    private boolean isPrimitive = false;
    //private Clazz jaldaClazz = null;

    public Struct(String name)
    {
        this.name = name;
    }

    public void generateHeader(PrintWriter pw)
    {
        String hash = (""+name.hashCode()).replace("-", "A");
        pw.println("typedef struct str_"+hash+" {");
        for(Field field: fields)
        {
            pw.println("\t"+field.getVariable().fullDeclaration()+";");
        }
        pw.println("} "+name+";");
    }

    public String toC(boolean asPointerIfApplicable)
    {
        if( isPrimitive )
            return name;
        else {
            //String result = "struct " + name;
            String result = name;
            if( asPointerIfApplicable )
                result += "*";
            return result;
        }
    }

    public Field getFieldByName(String name)
    {
        for(Field f: fields)
            if( f.getVariable().identifier.equals(name) )
                return f;

        throw new RuntimeException("could not find field with name '"+name+"' in Class '"+this.name+"'");
    }

    public boolean isPrimitive()
    {
        return isPrimitive;
    }

    public void setPrimitive(boolean primitive)
    {
        isPrimitive = primitive;
    }

    /*public Clazz getJaldaClazz() {
        return jaldaClazz;
    }

    public void setJaldaClazz(Clazz jaldaClazz) {
        this.jaldaClazz = jaldaClazz;
    }*/
}
