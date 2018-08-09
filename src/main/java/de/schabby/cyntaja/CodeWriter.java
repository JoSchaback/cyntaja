package de.schabby.cyntaja;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 *
 */
public class CodeWriter
{
    private Program index;
    private static final Logger log = LoggerFactory.getLogger(CodeWriter.class);

    public CodeWriter(Program index)
    {
        this.index = index;
    }

    public void generate(PrintWriter pw)
    {
        /*
        log.debug("writing code");
        pw.println("#include <stdio.h>");
        pw.println("#include <stdlib.h>");
        pw.println("#include <stdint.h>");

        pw.println("// ================[ STRUCT DECLARATIONS ]================");
        for( Struct struct: index.getAllStructs() ) {
            if( ! struct.isPrimitive() )
                struct.generateHeader(pw);
        }

        pw.println("// ================[ FUNCTION SIGNATURES ]================");
        for( Function func: index.getAllFunctions() ) {
            if( !(func.isConstructor() & func.getReturnType().isPrimitive()) )
                func.generateDeclaration(pw);
        }

        pw.println("// ================[ GLOBAL CONSTANT ]================");
        for(VariableDeclarationStatement varDec: index.getAllGlobalConstants()) {
            varDec.toC(pw, "");
            pw.println(";");
        }
        pw.println("// ================[ FUNCTION IMPLEMENTATIONS ]================");

        for( Function func: index.getAllFunctions() ) {
            if( !(func.isConstructor() && func.getReturnType().isPrimitive()) )
                func.generateBody(pw);
        }*/
    }
/*
    public static String translate(Clazz clazz) {
        return clazz.getName().replace(":", "_");
    }

    public static String translate(Function method) {
        if( method.isMethod() )
            return method.clazz.getName()+"_"+method.getName().replace(":", "_");
        else
            return method.getPackage().fullname.replace(":", "_") +"_"+method.getName();
    }*/
}
