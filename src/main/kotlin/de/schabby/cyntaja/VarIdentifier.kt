package de.schabby.cyntaja

class VarIdentifier(val variable:Variable) : LValue {
    override fun isPointer(): Boolean {
        return variable.type.isPointer
    }

    override fun writeCode(): String {
        return variable.name
    }

}