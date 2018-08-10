package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;

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

    public static void main(String[] args)
    {
        Program program = new Program();

        Function main = Helper.createMainFunction(program);

        Function fibonacci = new Function("fibonacci", Helper.int32);
        Variable nVar = new Variable(Helper.int32, "n");
        fibonacci.funcParams.add(nVar);

        If if1 = new If(new BinaryOperator("<=", nVar, new Literal("0")));
        if1.setIfStatement(new ReturnStatement(new Literal("0")));
        fibonacci.getBlock().add(if1);
        //ReturnStatement ret =

        If if2 = new If(new BinaryOperator("<=", nVar, new Literal("2")));
        if2.setIfStatement(new ReturnStatement(new Literal("1")));
        fibonacci.getBlock().add(if2);

        FunctionCall fc = new FunctionCall("fibonacci");
//        fc.params.add(  )

        program.add(Include.STDINT);
        program.add(fibonacci);

        String sourceFile = Constants.workingFolder + "fibonacci.c";
        String targetFile = Constants.workingFolder + "fibonacci.exe";

        program.writeToFile( sourceFile );

        int result = CompileExecutor.compile(Constants.pathToGCC, sourceFile, targetFile);
    }

}
