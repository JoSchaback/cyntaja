package de.schabby.cyntaja

class Casting (val type:Type, val exp:Expression) : Expression {
    override fun writeCode(): String ="(${type.name}) "+exp.writeCode()
}