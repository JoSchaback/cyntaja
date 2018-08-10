package de.schabby.cyntaja;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Central data structure (root) to hold the C syntax tree from which we later generate the target C file.
 */
public class Program {

    /**
     * The structs of the code, the name is prefixed by the package of the Jalda class.
     */
    private Map<String, Struct> structs = new HashMap<>();

    /**
     * C functions by name
     */
    private Map<String, Function> functions = new HashMap<>();

    private Set<Include> includes = new HashSet<>();

    private List<VariableDeclarationStatement> globalConstants = new ArrayList<>();

    public void add(Struct struct)
    {
        structs.put(struct.name, struct);
    }

    public void add(Function func) {
        if( functions.containsKey(func.getName()) )
            throw new RuntimeException("there is already a function with name '"+func.getName()+"'. Function overloading does not work in C.");

        functions.put(func.getName(), func);
    }

    public void add(Include inc) {
        this.includes.add(inc);
    }
/*
    public Struct getStruct(Clazz clazz)
    {
        Struct str = structs.get(CodeWriter.translate(clazz));

        if( str == null )
            throw new RuntimeException("could not find Struct in CFile for class "+clazz.getFullName()+" ("+ CodeWriter.translate(clazz)+")");

        return str;
    }*/

    public Struct getStruct(String typeIdentifier)
    {
        return structs.get(typeIdentifier);
    }
/*
    public boolean contains(Clazz clazz)
    {
        return structs.containsKey(CodeWriter.translate(clazz));
    }

    public Function getFunction(Function jaldaFunc) {
        if( jaldaFunc == null )
            throw new RuntimeException("Jalda Function is null");

        for( Function func: functions.values() ) {
            if( func.getJaldaFunction() != null && func.getJaldaFunction().equals(jaldaFunc) )
                return func;
        }

        return null;
    } */

    public void renameFunction(Function func, String newName) {
        functions.remove(func.getName());
        func.setName(newName);
        functions.put(func.getName(), func);
    }

    /**
     * Writes this Program to a C source file.
     *
     * @param filename filename to source file
     */
    public void writeToFile(String filename) {
        try {
            PrintWriter pw = new PrintWriter(filename);

            // write include statements at beginning of file
            includes.forEach( i -> i.toC(pw) );

            // write function declarations
            functions.values().stream()
                    .filter( Function::isRequiresDeclaration )
                    .forEach( f -> f.generateDeclaration(pw));

            // write function definitions
            functions.values().forEach( f -> f.generateBody(pw) );

            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Function getFunction(String name) {
        return functions.get(name);
    }

    public int getFunctionCount() { return functions.size(); }

    public Collection<Function> getAllFunctions() { return functions.values();  }

    public Collection<Struct> getAllStructs() {
        return structs.values();
    }

    public void addGlobalConstant(VariableDeclarationStatement varDec) {
        this.globalConstants.add(varDec);
    }

    public int getClobalConstantsCount() {
        return globalConstants.size();
    }

    public Collection<VariableDeclarationStatement> getAllGlobalConstants() {
        return this.globalConstants;
    }

    public boolean containsFunctionWithName(String name) { return functions.containsKey(name); }

    public void removeStruct(Struct struct) {
        this.structs.remove(struct.name);
    }

    public void removeFunction(Function func)
    {
        this.functions.remove(func.getName());
    }

}
