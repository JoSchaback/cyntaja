package de.schabby.cyntaja

open class Type(val name:String, val isPointer:Boolean=false) : Writable {
    override fun writeCode(): String {
        return name
    }

    companion object {
        val void = Type("void")
        val int  = Type("int32_t")
        val char  = Type("char")
        val voidPtr = Type("void*", true)
        val intPtr  = Type("int*", true)
        val charPtr  = Type("char*", true)
    }

}



