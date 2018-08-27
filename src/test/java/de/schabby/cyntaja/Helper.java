package de.schabby.cyntaja;

/**
 * Utility class to generate often and commonly used code blocks, such as
 * main functions and malloc calls.
 */
public class Helper {

    public static final BasicType INT32  = new BasicType("int32_t");
    public static final BasicType UINT32 = new BasicType("uint32_t");
    public static final BasicType INT8   = new BasicType("int8_t");
    public static final BasicType UINT8  = new BasicType("uint8_t");

    public static Function createMainFunction(Program program)
    {
        Function main = new Function("main", INT32);
        main.setRequiresDeclaration(false);
        program.add(Include.STDINT);
        program.add(main);

        return main;
    }

}
