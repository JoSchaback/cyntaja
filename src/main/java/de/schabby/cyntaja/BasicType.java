package de.schabby.cyntaja;

/**
 * Basic data type in the C programming language.
 */
public class BasicType implements Type {

    private final String type;

    public BasicType(String type) {
        this.type = type;
    }


    /*
    public BasicType(Clazz clazz)
    {
        this.isArray = false;

        if( clazz.getName().equals("Int")
                | clazz.getName().equals("Float")
                | clazz.getName().equals("Void")
                | clazz.getName().equals("Long"))
        {
            this.isPrimitive = true;
            this.isPointer = false;
            if( clazz.getName().equals("Int") ) type = "int";
            else if( clazz.getName().equals("Float") ) type = "float";
            else if( clazz.getName().equals("Void") ) type = "void";
            else if( clazz.getName().equals("Long") ) type = "long";
            else throw new RuntimeException("can not create type "+clazz.getName());
        }
        else
        {
            this.isPrimitive = false;
            this.isPointer = true;
            this.type = CodeWriter.translate(clazz);
        }
    }*/

    public String toC()
    {
        return type;
    }

    public String getType() {
        return type;
    }

    public BasicType getBasicType() {
        return this;
    }
}
