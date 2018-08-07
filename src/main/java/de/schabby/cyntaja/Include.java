package de.schabby.cyntaja;

import java.io.PrintWriter;

/**
 * #include pre-processor directive for the C programming language. Currently only supports the predetermined
 * path variant (ie. <...> notation).
 */
public class Include {

    /**
     * includes the stdio.h header file.
     */
    public static final Include STDIO = new Include("stdio.h");

    public static final Include STDLIB = new Include("stdlib.h");

    public static final Include STDINT = new Include("stdint.h");

    public Include(String lib) {
        this.lib = lib;
    }

    public final String lib;

    public void toC(PrintWriter pw) {
        pw.println("#include<"+lib+">");
    }
}
