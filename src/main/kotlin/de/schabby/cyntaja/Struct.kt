package de.schabby.cyntaja

import java.lang.RuntimeException

class Struct(name:String) : Writable, ValueType(name) {

    val fields = mutableListOf<Variable>()

    override fun writeCode(): String {
        val sb = StringBuilder()
        sb.append("struct $name {\n")
        fields.forEach {
            sb.append("   ")
            sb.append(it.type.name+" "+it.name+";\n")
        }
        sb.append("};")
        return sb.toString()
    }

    fun getFieldByVarName(varName:String) = fields.find {it.name==varName}!!

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