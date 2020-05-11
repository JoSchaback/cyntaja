package de.schabby.cyntaja

import java.lang.StringBuilder

class FunctionPointer() : Type("function_pointer") {

    var returnType: Type = Type.void
    val parameterTypes = mutableListOf<Type>()

    constructor(returnType:Type, vararg params:Type) : this() {
        this.returnType = returnType
        parameterTypes.addAll(params)
    }

    fun writeFunctionPointer(): String {
        val sb = StringBuilder("${returnType.name} (*$name) (")
        parameterTypes.forEachIndexed { index, type ->
            sb.append(type.writeCode())
            if( index < parameterTypes.size-1 )
                sb.append(", ")
        }
        sb.append(")")
        return sb.toString()
    }

}