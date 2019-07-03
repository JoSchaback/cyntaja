package de.schabby.cyntaja

class Literal(val value:String) : Expression {

    override fun writeCode() = value

    companion object {
        fun fromInt(int:Int) : Literal = Literal(int.toString())
    }
}

val NULL = Literal("NULL")