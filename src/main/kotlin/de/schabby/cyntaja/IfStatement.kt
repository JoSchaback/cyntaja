package de.schabby.cyntaja

class IfStatment(val booleanExp: Expression, val parent:StatementBlock) : Statement, VariableContainer {

    var thenBlock = StatementBlock(this)
    var elseBlock = StatementBlock(this)

    override fun writeCode(): String {
        val sb = StringBuilder()
        sb.append("if(")
        sb.append(booleanExp.writeCode())
        sb.append(") {\n")
        sb.append(thenBlock.writeCode())
        sb.append("}\n")
        if( elseBlock.list.size > 0 ) {
            sb.append("else {\n")
            sb.append(elseBlock.writeCode())
            sb.append("}")
        }

        return sb.toString()
    }

    fun then(block:StatementBlock.()->Unit) {
        thenBlock.apply(block)
    }

    fun otherwise(block:StatementBlock.()->Unit) {
        elseBlock.apply(block)
    }


    override fun findStruct(name: String): Struct = parent.findStruct(name)

    override fun findVar(name: String): Variable = parent.findVar(name)


}