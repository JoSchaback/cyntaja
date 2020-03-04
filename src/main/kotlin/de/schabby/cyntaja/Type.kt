package de.schabby.cyntaja

open class Type(var name:String) : Writable {

    override fun writeCode(): String {
        return name
    }

    companion object {
        val void  = ValueType("void")
        val bool  = ValueType("bool")
        val i8    = ValueType("int8_t")
        val u8    = ValueType("uint8_t")
        val i16   = ValueType("int16_t")
        val u16   = ValueType("uint16_t")
        val i32   = ValueType("int32_t")
        val u32   = ValueType("uint32_t")
        val i64   = ValueType("int64_t")
        val u64   = ValueType("uint64_t")
        val i128  = ValueType("int128_t")
        val u128  = ValueType("uint128_t")
        val float = ValueType("float")
        val double= ValueType("double")
        val char  = ValueType("char")
    }

}

open class ValueType(name:String) : Type(name) {

    val asPointer = PointerType(this)

}

class PointerType(valueType:ValueType) : Type(valueType.name+"*") {

}
