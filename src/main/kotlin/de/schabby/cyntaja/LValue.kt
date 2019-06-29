package de.schabby.cyntaja

/**
 * Locator Value, e.g. variable names, array access, struct fields are locator values, but
 * also RefOperators and DotOperators
 */
interface LValue : Expression {
    fun isPointer() : Boolean
}