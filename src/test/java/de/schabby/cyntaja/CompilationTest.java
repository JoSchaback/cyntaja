package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;

public class CompilationTest {

    public static void main(String[] args)
    {
        Index index = new Index();

        Type integer    = new Type("int", false, true, false);
        Type voidStruct = new Type("void", false, true, false);

        Function main = new Function("main", integer);
        main.setRequiresDeclaration(false);

        FunctionCall call = new FunctionCall("printf");
        call.params.add( new Literal("Hello World!", true) );

        ReturnStatement ret = new ReturnStatement(new Literal("0", false));

        main.getBlock().add(call);
        main.getBlock().add(ret);

        index.add(Include.STDIO);
        index.add(main);

        String pathToGCC="C:\\Users\\johan\\Downloads\\codeblocks-17.12mingw-nosetup\\MinGW\\bin\\gcc.exe";
        String sourceFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.c";
        String targetFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.exe";

        index.writeToFile( sourceFile );

        int result = CompileExecutor.compile(pathToGCC, sourceFile, targetFile);
    }

}
