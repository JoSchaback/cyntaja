package de.schabby.cyntaja

class Sizeof(val type:Type) : Expression{
    override fun writeCode(): String = "sizeof(${type.name})"
}

