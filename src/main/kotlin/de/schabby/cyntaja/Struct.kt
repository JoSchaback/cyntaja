package de.schabby.cyntaja

class Struct(name:String) : Writable, Type(name) {
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

    fun field(name:String, type:Type) {
        fields.add(Variable(name, type))
    }
}