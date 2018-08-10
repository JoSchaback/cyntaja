package de.schabby.cyntaja;

import java.io.PrintWriter;

public class Variable implements Expression {

    public final Type type;

    public final String identifier;
    private boolean isFunctionParameter = false;
    private boolean isStatic = false;
    private boolean isConst  = false;

    public Variable(Type type, String identifier){
        this.identifier = identifier;
        this.type = type;
    }

    public String typeDeclaration() {
        //String typeId = type == null ? typeIdentifier : type.name;
        String typeId = type.toC();
        //boolean isPrimitve = type != null ? type.isPrimitive() : true;

        //if( !isPrimitve )
        //    typeId = typeId;
/*
        if( isPointer )
            typeId += "*";

        if( isHeapArray )
            typeId += "*";
*/
        return typeId;
    }

    public String fullDeclaration() {
        String modifier = "";

        if( isStatic )
            modifier += "static ";

        if( isConst )
            modifier += "const ";

        return modifier + typeDeclaration() + " "+identifier;
    }

    public boolean isFunctionParameter() {
        return isFunctionParameter;
    }

    public void setFunctionParameter(boolean functionParameter) {
        isFunctionParameter = functionParameter;
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

    @Override
    public void toC(PrintWriter pw) {
        pw.print(identifier);
    }

    @Override
    public void visit(Visitor visitor) {

    }
}
