package de.schabby.cyntaja

class Literal(val value:String) : Expression {

    override fun writeCode() = value

}

val NULL = Literal("NULL")