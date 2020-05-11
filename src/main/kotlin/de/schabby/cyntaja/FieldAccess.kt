package de.schabby.cyntaja

/**
 * Either dot (.) or arrow (->) depending on whether lvalue is pointer or not.
 */
class FieldAccess(val left:LValue, val right:Expression) : LValue {

    override fun isPointer(): Boolean {
        return if( right is LValue ) { right.isPointer() } else {false}
    }

    init {
      //  if (left.type !is Struct) throw RuntimeException("FieldAccess references into Structs, but lvalue is of type ${left.type.name}")
    }

    override fun writeCode(): String {
        val sb = StringBuilder()
        sb.append(left.writeCode())
        if( left.isPointer() )
            sb.append("->")
        else
            sb.append(".")
        sb.append(right.writeCode())

        return sb.toString()
    }

}