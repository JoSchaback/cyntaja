package de.schabby.cyntaja

interface Expression : Writable {

}

object EmptyExpression : Expression {
    override fun writeCode() = "()"

}