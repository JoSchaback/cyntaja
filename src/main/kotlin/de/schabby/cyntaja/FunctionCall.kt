package de.schabby.cyntaja

fun commaIfLast(list:List<Any>, index:Int, sb:StringBuilder) {
    if( index < list.size-1 )
        sb.append(", ")
}

class FunctionCall(val function:Function) : Statement, Expression {

    val parameters = mutableListOf<Expression>()

    override fun writeCode(): String {
        val sb = StringBuilder()
        sb.append(function.name)
        sb.append('(')
        parameters.forEachIndexed { index, t ->
            sb.append(t.writeCode())
            commaIfLast(parameters, index, sb)
        }
        sb.append(')')

        return sb.toString()
    }
}

class FunctionCallLibrary(val funcName:String) : Statement, Expression {

    val parameters = mutableListOf<Expression>()

    override fun writeCode(): String {
        val sb = StringBuilder()
        sb.append(funcName)
        sb.append('(')
        parameters.forEachIndexed { index, t ->
            sb.append(t.writeCode())
            commaIfLast(parameters, index, sb)
        }
        sb.append(')')

        return sb.toString()
    }


}

