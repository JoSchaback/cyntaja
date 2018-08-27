package de.schabby.cyntaja;

/**
 * Utility class to generate often and commonly used code blocks, such as
 * main functions and malloc calls.
 */
public class Helper {

    public static final BasicType int32  = new BasicType("int32_t");
    public static final BasicType uint32 = new BasicType("uint32_t");
    public static final BasicType int8   = new BasicType("int8_t");
    public static final BasicType uint8  = new BasicType("uint8_t");

    public static Function createMainFunction(Program program)
    {
        Function main = new Function("main", int32);
        main.setRequiresDeclaration(false);
        program.add(Include.STDINT);
        program.add(main);

        return main;
    }

}
