package de.schabby.cyntaja

/**
 * Static String literal expression, such as "Hello, World!"
 */
class StringLiteral(val value:String) : Expression {
    override fun writeCode() = "\"$value\""

}

/**
 * Static single character literal expression, such as 'a'.
 */
class CharLiteral(val value:Char) : Expression {
    override fun writeCode() = "'$value'"

}