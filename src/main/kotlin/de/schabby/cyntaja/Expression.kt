package de.schabby.cyntaja

interface Expression : Writable {

}

object emptyExpression : Expression {
    override fun writeCode() = "()"

}