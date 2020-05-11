package de.schabby.cyntaja

class FunctionPointerIdentifier(var function:Function) : LValue {

    override fun isPointer(): Boolean {
        return true
    }

    override fun writeCode() = function.name

}