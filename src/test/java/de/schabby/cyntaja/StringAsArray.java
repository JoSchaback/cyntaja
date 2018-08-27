package de.schabby.cyntaja;

import de.schabby.cyntaja.tools.CompileExecutor;
import org.junit.Test;

/**
 * Simple C program:
 *
 * int32_t main() {
 *    int8_t* array = (int8_t*) malloc(sizeof(int8_t) * 10);
 * 	  printf("Hello World!");
 * 	  free(int8_t);
 * 	  return 0;
 * }
 *
 */
public class StringAsArray {

    @Test
    public void stringsToArray()
    {
        BasicType int32   = new BasicType("int32_t");
        BasicType int8    = new BasicType("int8_t");
        PtrType int8Array = new PtrType(int8);

        Function main = new Function("main", int32);
        main.setRequiresDeclaration(false);

        // int8_t* array = (int8_t*) malloc(sizeof(int8_t) * 10);
        // first we do the right hand side of the = declaration
        FunctionCall sizeof = new FunctionCall("sizeof", new TypeExpression(int8Array.getBasicType()));
        BinaryOperator mult = new BinaryOperator("*", sizeof, new Literal("10"));
        FunctionCall malloc = new FunctionCall("malloc", mult);
        Cast cast = new Cast(int8Array, malloc);
        // now the left hand side
        Variable array = new Variable(int8Array, "array");
        VariableDeclarationStatement vds = new VariableDeclarationStatement(array, cast);

        AssignmentStatement ass0 = new AssignmentStatement(new ArrayAccess(array, new Literal("0")), "=", new Literal("104"));
        AssignmentStatement ass1 = new AssignmentStatement(new ArrayAccess(array, new Literal("1")), "=", new Literal("97"));
        AssignmentStatement ass2 = new AssignmentStatement(new ArrayAccess(array, new Literal("2")), "=", new Literal("108"));
        AssignmentStatement ass3 = new AssignmentStatement(new ArrayAccess(array, new Literal("3")), "=", new Literal("108"));
        AssignmentStatement ass4 = new AssignmentStatement(new ArrayAccess(array, new Literal("4")), "=", new Literal("111"));
        AssignmentStatement ass5 = new AssignmentStatement(new ArrayAccess(array, new Literal("5")), "=", new Literal("32"));
        AssignmentStatement ass6 = new AssignmentStatement(new ArrayAccess(array, new Literal("6")), "=", new Literal("66"));
        AssignmentStatement ass7 = new AssignmentStatement(new ArrayAccess(array, new Literal("7")), "=", new Literal("111"));
        AssignmentStatement ass8 = new AssignmentStatement(new ArrayAccess(array, new Literal("8")), "=", new Literal("104"));
        AssignmentStatement ass9 = new AssignmentStatement(new ArrayAccess(array, new Literal("9")), "=", new Literal("114"));
        AssignmentStatement ass10 = new AssignmentStatement(new ArrayAccess(array, new Literal("10")), "=", new Literal("0"));

        FunctionCall call = new FunctionCall("puts", array);

        FunctionCall free = new FunctionCall("free", array);

        ReturnStatement ret = new ReturnStatement(new Literal("0", false));

        main.getBlock().add(vds);
        main.getBlock().add(ass0);
        main.getBlock().add(ass1);
        main.getBlock().add(ass2);
        main.getBlock().add(ass3);
        main.getBlock().add(ass4);
        main.getBlock().add(ass5);
        main.getBlock().add(ass6);
        main.getBlock().add(ass7);
        main.getBlock().add(ass8);
        main.getBlock().add(ass9);
        main.getBlock().add(ass10);
        main.getBlock().add(call);
        main.getBlock().add(free);
        main.getBlock().add(ret);

        Program program = new Program();
        program.add(Include.STDIO);
        program.add(Include.STDINT);
        program.add(Include.STDLIB);
        program.add(main);

        String sourceFile = Constants.workingFolder + "string_as_array.c";
        String targetFile = Constants.workingFolder + "string_as_array.exe";

        CompileExecutor.writeToFileAndCompile(program, Constants.pathToGCC, sourceFile, targetFile);
    }

}
