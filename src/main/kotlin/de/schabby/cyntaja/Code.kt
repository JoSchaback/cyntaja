package de.schabby.cyntaja

/**
 * Holds C source code which gets injected in the StatementBody such that
 * users can write C code directly.
 */
class Code(val code:String) : Statement {
    override fun writeCode(): String {
        return code
    }

}