package de.schabby.cyntaja

class ArraySubscript(val variable: Variable, val subscript: Expression) : LValue {

    override fun isPointer(): Boolean {
        return variable.type is PointerType
    }

    init {
        // check that variable.type is of pointer
    }

    override fun writeCode(): String {
        return variable.name+"["+subscript.writeCode()+"]"
    }

}