package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;
import org.junit.Test;

public class HelloWorld {

    @Test
    public void helloWorld()
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

        final String sourceFile = Constants.workingFolder + "main.c";
        final String targetFile = Constants.workingFolder + "main.exe";
        CompileExecutor.writeToFileAndCompile(program, Constants.pathToGCC, sourceFile, targetFile);
    }

}
