package de.schabby.cyntaja

class Address(val variable:Variable) : LValue {

    override fun isPointer(): Boolean {
        return variable.type is PointerType
    }

    override fun writeCode(): String {
        return "&"+variable.name
    }

}