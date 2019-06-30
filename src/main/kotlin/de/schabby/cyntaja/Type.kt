package de.schabby.cyntaja

open class Type(val name:String) : Writable {

    override fun writeCode(): String {
        return name
    }

    companion object {
        val void  = ValueType("void")
        val int   = ValueType("int32_t")
        val float = ValueType("float")
        val uint  = ValueType("uint32_t")
        val char  = ValueType("char")
    }

}

open class ValueType(name:String) : Type(name) {

    val asPointer = PointerType(this)

}

class PointerType(valueType:ValueType) : Type(valueType.name+"*") {

}