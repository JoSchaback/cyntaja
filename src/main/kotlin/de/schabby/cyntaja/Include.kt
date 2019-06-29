package de.schabby.cyntaja

/**
 * #include pre-processor directive for the C programming language. Currently only supports the predetermined
 * path variant (ie. <...> notation).
 */
class Include(val lib: String) : Writable{

    override fun writeCode() = "#include<$lib>"

    companion object {

        /**
         * includes the stdio.h header file.
         */
        val STDIO = Include("stdio.h")

        val STDLIB = Include("stdlib.h")

        val STDINT = Include("stdint.h")
    }
}
