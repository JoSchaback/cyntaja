package de.schabby.cyntaja

import java.lang.RuntimeException

class Struct(name:String) : Writable, ValueType(name) {

    override fun writeCode(): String {
        val sb = StringBuilder()
        sb.append("typedef struct {\n")
        fields.forEach {
            sb.append("   ")
            sb.append(it.type.name+" "+it.name+";\n")
        }
        sb.append("} $name;")
        return sb.toString()
    }

    val fields = mutableListOf<Variable>()

    /**
     * Adds a new field to this Struct.
     */
    fun field(name:String, type:Type) {
        fields.add(Variable(name, type))
    }

    /**
     * Returns the field with the given name. If the
     * field is not present, this method crashes.
     */
    fun field(name:String) : Variable {
        val result = fields.filter { it.name.equals(name) }
        if(result.isEmpty()) throw RuntimeException("could not find field '$name' in Struct '${this.name}'")
        return result.first()
    }

}