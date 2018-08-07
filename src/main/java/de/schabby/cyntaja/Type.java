package de.schabby.cyntaja;


public class Type {

    public final String identifier;
    public final boolean isPointer;
    public final boolean isPrimitive;
    public final boolean isArray;

    public Type(String identifier, boolean isPointer, boolean isPrimitive, boolean isArray) {
        this.identifier = identifier;
        this.isPointer = isPointer;
        this.isPrimitive = isPrimitive;
        this.isArray = isArray;
    }


    /*
    public Type(Clazz clazz)
    {
        this.isArray = false;

        if( clazz.getName().equals("Int")
                | clazz.getName().equals("Float")
                | clazz.getName().equals("Void")
                | clazz.getName().equals("Long"))
        {
            this.isPrimitive = true;
            this.isPointer = false;
            if( clazz.getName().equals("Int") ) identifier = "int";
            else if( clazz.getName().equals("Float") ) identifier = "float";
            else if( clazz.getName().equals("Void") ) identifier = "void";
            else if( clazz.getName().equals("Long") ) identifier = "long";
            else throw new RuntimeException("can not create type "+clazz.getName());
        }
        else
        {
            this.isPrimitive = false;
            this.isPointer = true;
            this.identifier = CodeWriter.translate(clazz);
        }
    }*/

    public String toC(boolean asPointerIfApplicable)
    {
        if( isPrimitive )
            return identifier;
        else {
            String result = "struct " + identifier;
            if( asPointerIfApplicable )
                result += "*";
            return result;
        }
    }
}
