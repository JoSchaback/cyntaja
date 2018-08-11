package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Computes the Fibonacci series recursively.
 *
 * #include<stdio.h>
 * void printFibonacci(int n){
 *     static int n1=0,n2=1,n3;
 *     if(n>0){
 *          n3 = n1 + n2;
 *          n1 = n2;
 *          n2 = n3;
 *          printf("%d ",n3);
 *          printFibonacci(n-1);
 *     }
 * }
 * int main(){
 *     int n;
 *     printf("Enter the number of elements: ");
 *     scanf("%d",&n);
 *     printf("Fibonacci Series: ");
 *     printf("%d %d ",0,1);
 *     printFibonacci(n-2);//n-2 because 2 numbers are already printed
 *   return 0;
 *  }
 *
 */
public class Fibonacci {

    @Test
    public void fibonacci()
    {
        Program program = new Program();

        Function main = Helper.createMainFunction(program);

        Function fibonacci = new Function("fibonacci", Helper.int32);
        Variable nVar = new Variable(Helper.int32, "n");
        fibonacci.funcParams.add(nVar);

        If if1 = new If(new BinaryOperator("==", nVar, new Literal("0")));
        if1.setIfStatement(new ReturnStatement(new Literal("0")));
        fibonacci.getBlock().add(if1);
        //ReturnStatement ret =

        If if2 = new If(new BinaryOperator("==", nVar, new Literal("1")));
        if2.setIfStatement(new ReturnStatement(new Literal("1")));
        fibonacci.getBlock().add(if2);

        FunctionCall fcMinus1 = new FunctionCall("fibonacci");
        fcMinus1.params.add( new BinaryOperator("-", nVar, new Literal("1")));

        FunctionCall fcMinus2 = new FunctionCall("fibonacci");
        fcMinus2.params.add( new BinaryOperator("-", nVar, new Literal("2")));

        ReturnStatement retStat = new ReturnStatement(new BinaryOperator("+", fcMinus1, fcMinus2));

        fibonacci.getBlock().add(retStat);

        FunctionCall call = new FunctionCall("printf");
        call.params.add( new Literal("Fibonacci number of 10 is %i", true) );
        call.params.add( new FunctionCall("fibonacci", new Literal("10")) );

        main.getBlock().add(call);
        main.getBlock().add(new ReturnStatement(new Literal("0")));

        program.add(Include.STDINT);
        program.add(Include.STDIO);
        program.add(fibonacci);

        final String sourceFile = Constants.workingFolder + "fibonacci.c";
        final String targetFile = Constants.workingFolder + "fibonacci.exe";
        CompileExecutor.writeToFileAndCompile(program, Constants.pathToGCC, sourceFile, targetFile);
    }

}
