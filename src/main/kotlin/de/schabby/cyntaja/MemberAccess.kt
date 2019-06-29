package de.schabby.cyntaja

/**
 * Either dot (.) or arrow (->) depending on whether lvalue is pointer or not.
 */
class MemberAccess(val left:LValue, val right:LValue) : LValue {

    override fun isPointer(): Boolean = right.isPointer()

    init {
      //  if (left.type !is Struct) throw RuntimeException("MemberAccess references into Structs, but lvalue is of type ${left.type.name}")
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