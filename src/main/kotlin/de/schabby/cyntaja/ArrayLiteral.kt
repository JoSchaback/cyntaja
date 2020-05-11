package de.schabby.cyntaja

import java.lang.StringBuilder

class ArrayLiteral() : LValue {
    val values = mutableListOf<Expression>()
    override fun isPointer(): Boolean {
        return false
    }

    constructor(vararg vs: Expression) : this() {
        values.addAll(vs)
    }

    constructor(vararg vs: String) : this() {
        vs.forEach { values.add( Literal(it) ) }
    }


    override fun writeCode(): String {
        val sb = StringBuilder("{")
        values.forEachIndexed { index, lValue ->
            sb.append(lValue.writeCode())
            if( index < values.size-1 ) sb.append(", ")  }
        sb.append("}")
        return sb.toString()
    }
}