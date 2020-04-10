package de.schabby.cyntaja

import de.schabby.cyntaja.Type.Companion.void

class Function(var name:String, val program: Program) : Writable /*, VariableContainer */ {
/*
    override fun findStruct(name: String): Struct = program.findStruct(name)

    override fun findVar(name: String): Variable {
        parameters.list.forEach { p->
            if (p.name.equals(name)) return p
        }

        throw RuntimeException("could not find variable with name $name")
    } */

    val parameters = FunctionParameters()
    var returnType : Type = void
    val body = StatementBlock(/*this*/)
    var inline = false // add inline to signature

    override fun writeCode(): String {
        val sb = StringBuilder()
        if( inline )
            sb.append("inline ")
        sb.append(returnType.name)
        sb.append(" ")
        sb.append(name)
        sb.append('(')
        sb.append(parameters.writeCode())
        sb.append(") {\n")
        sb.append(body.writeCode())
        sb.append("}")
        return sb.toString()
    }

    fun parameters(block: FunctionParameters.() -> Unit) {
        parameters.block()
    }

    fun body(block: StatementBlock.()->Unit) {
        body.apply(block)
    }

    fun writeDeclaration(): String {
        val sb = StringBuilder()
        /* if( inline )
            sb.append("inline ") */
        sb.append(returnType.name)
        sb.append(" ")
        sb.append(name)
        sb.append('(')
        sb.append(parameters.writeCode())
        sb.append(");\n")
        return sb.toString()
    }
}

class FunctionParameters() : Writable {
    val list = mutableListOf<Variable>()

    override fun writeCode(): String {
        val sb = StringBuilder()
        list.forEachIndexed { index, t ->
            sb.append(t.type.name)
            sb.append(" ")
            sb.append(t.name)
            commaIfLast(list, index, sb)
        }
        return sb.toString()
    }

    fun variable(name:String, type: Type) : Variable {
        val v = Variable(name, type)
        list.add(v)
        return v
    }

}
