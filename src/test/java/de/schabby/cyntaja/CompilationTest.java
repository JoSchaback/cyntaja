package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;

public class CompilationTest {

    public static void main(String[] args)
    {
        Program program = new Program();

        Type integer    = new Type("int", false, true, false);
        Type voidStruct = new Type("void", false, true, false);

        Function main = new Function("main", integer);
        main.setRequiresDeclaration(false);

        FunctionCall call = new FunctionCall("printf");
        call.params.add( new Literal("Hello World!", true) );

        ReturnStatement ret = new ReturnStatement(new Literal("0", false));

        main.getBlock().add(call);
        main.getBlock().add(ret);

        program.add(Include.STDIO);
        program.add(main);

        String pathToGCC="C:\\MinGW\\bin\\gcc.exe";
        String sourceFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.c";
        String targetFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.exe";

        program.writeToFile( sourceFile );

        int result = CompileExecutor.compile(pathToGCC, sourceFile, targetFile);
    }

}
