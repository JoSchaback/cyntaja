package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;

public class CompilationTest {

    public static void main(String[] args)
    {
        BasicType integer = new BasicType("int");

        Function main = new Function("main", integer);
        main.setRequiresDeclaration(false);

        FunctionCall call = new FunctionCall("printf");
        call.params.add( new Literal("Hello World!", true) );

        ReturnStatement ret = new ReturnStatement(new Literal("0", false));

        main.getBlock().add(call);
        main.getBlock().add(ret);

        Program program = new Program();
        program.add(Include.STDIO);
        program.add(main);

        String sourceFile = Constants.workingFolder + "main.c";
        String targetFile = Constants.workingFolder + "main.exe";

        program.writeToFile( sourceFile );

        int result = CompileExecutor.compile(Constants.pathToGCC, sourceFile, targetFile);
    }

}
