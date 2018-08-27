package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;
import org.junit.Test;

/**

 *
 */
public class ForLoop {

    @Test
    public void forLoop()
    {
        Program program = new Program();

        Function main = Helper.createMainFunction(program);

        Variable sumVar = new Variable(Helper.UINT8, "sum");
        main.getBlock().add(new VariableDeclarationStatement(sumVar, new Literal("0")));

        For forLoop = new For();
        Variable counterVar = new Variable(Helper.UINT8, "counter");
        forLoop.setInitStmnt( new VariableDeclarationStatement(counterVar, new Literal("0")) );
        forLoop.setConditional( new BinaryOperator("<", counterVar, new Literal("5")) );
        forLoop.setIncrement( new UnaryOperator("++", counterVar, false));

        Block forBlock = new Block();
        forLoop.setExecStatement(forBlock);

        forBlock.add( new AssignmentStatement(sumVar, counterVar, "+=") );

        FunctionCall call = new FunctionCall("printf");
        call.params.add( new Literal("loop number of %i is sum as %i\\n", true) );
        call.params.add( counterVar );
        call.params.add( sumVar );

        forBlock.add(call);

        main.getBlock().add(forLoop);
        main.getBlock().add(new ReturnStatement(new Literal("0")));

        program.add(Include.STDINT);
        program.add(Include.STDIO);

        final String sourceFile = Constants.workingFolder + "forloop.c";
        final String targetFile = Constants.workingFolder + "forloop.exe";
        CompileExecutor.writeToFileAndCompile(program, Constants.pathToGCC, sourceFile, targetFile);
    }

}
