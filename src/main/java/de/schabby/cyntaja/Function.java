package de.schabby.cyntaja;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a C function.
 */
public class Function {

    /**
     * Name of the function.
     */
    private String name;

    /**
     * Reference to the return type. Is Void in case it does not return anything.
     */
    private Type returnType;

    /**
     * Pointer to the Struct/Class it used to belong to in Jalda. Necessary because we need to resolve
     * field and other methods properly sometimes.
     */
    private Struct belongsTo = null;

    /**
     * Flag whether this function is a constructor.
     */
    private boolean isConstructor = false;

    private boolean requiresDeclaration = true;

    /**
     * List of function parameters.
     */
    public final List<Variable> funcParams  = new ArrayList<>();

    /**
     * List of function body block.
     */
    private Block block = new Block();

    public Function(String name, Type returnType)
    {
        this.name = name;
        this.returnType = returnType;
    }

    private void generateHeader(PrintWriter pw)
    {
        pw.print( returnType.toC(true)+" ");

        pw.print(name+"(");

        for( int i = 0; i < funcParams.size(); i++)
        {
            Variable var = funcParams.get(i);
            pw.print(var.fullDeclaration());
            if( i < funcParams.size() - 1 )
                pw.print(", ");
        }
        pw.print(") ");
    }

    public void generateDeclaration(PrintWriter pw)
    {
        this.generateHeader(pw);
        pw.println(";");
    }

    public void generateBody(PrintWriter pw)
    {
        generateHeader(pw);
        if( isConstructor )
            pw.print("/* constructor for struct "+belongsTo.name+" */ ");

        block.toC(pw, "");
        pw.println();
        pw.println();
    }

    public void visit(Visitor visitor)
    {
        visitor.function(this);

        if( block == null )
            throw new RuntimeException("Function '"+getName()+"' has not Block (null)");

        block = (Block) visitor.statement(block);
        block.visit(visitor);
    }

    public Struct getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Struct belongsTo) {
        this.belongsTo = belongsTo;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public String getName() {  return name; }

    void setName(String name) {    this.name = name; }

    public boolean isRequiresDeclaration() { return requiresDeclaration; }

    public void setRequiresDeclaration(boolean requiresDeclaration) {this.requiresDeclaration = requiresDeclaration; }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
