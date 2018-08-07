package de.schabby.cyntaja;

public class Variable {

    private Struct type;
    private boolean isPointer = false;

    public final String typeIdentifier;
    public final String name;
    private boolean isClassField = false;
    private boolean isFunctionParameter = false;
    private boolean isHeapArray = false;
    private boolean isStackArray = false;
    private boolean isStatic = false;
    private boolean isConst  = false;

    public Variable(String typeIdentifier, String name) {
        this.typeIdentifier = typeIdentifier;
        this.name = name;
    }

    public Variable(Struct type, String name){
        this(type.name, name);
        this.type = type;
    }

    public String typeDeclaration() {
        String typeId = type == null ? typeIdentifier : type.name;

        //boolean isPrimitve = type != null ? type.isPrimitive() : true;

        //if( !isPrimitve )
        //    typeId = typeId;

        if( isPointer )
            typeId += "*";

        if( isHeapArray )
            typeId += "*";

        return typeId;
    }

    public String fullDeclaration() {
        String modifier = "";

        if( isStatic )
            modifier += "static ";

        if( isConst )
            modifier += "const ";

        return modifier + typeDeclaration() + " "+name + (isStackArray?"[]":"");
    }

    public Struct getType() {
        return type;
    }

    public void setType(Struct type) {
        this.type = type;
    }

    public boolean isClassField() {
        return isClassField;
    }

    public void setClassField(boolean classField) {
        isClassField = classField;
    }

    public boolean isFunctionParameter() {
        return isFunctionParameter;
    }

    public void setFunctionParameter(boolean functionParameter) {
        isFunctionParameter = functionParameter;
    }

    public boolean isHeapArray() {
        return isHeapArray;
    }

    public void setHeapArray(boolean heapArray) {
        isHeapArray = heapArray;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isConst() {
        return isConst;
    }

    public void setConst(boolean aConst) {
        isConst = aConst;
    }

    public boolean isPointer() {return isPointer;}

    public void setPointer(boolean pointer) {isPointer = pointer;}

    public boolean isStackArray() { return isStackArray; }

    public void setStackArray(boolean stackArray) { isStackArray = stackArray; }
}
