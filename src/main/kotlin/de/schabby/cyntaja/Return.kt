package de.schabby.cyntaja

/**
 * Return statement with subsequent Expression to return values from function calls.
 */
class Return(val exp:Expression) : Statement{
    override fun writeCode(): String = "return "+exp.writeCode()

}