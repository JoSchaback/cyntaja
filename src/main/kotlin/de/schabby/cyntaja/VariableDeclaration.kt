package de.schabby.cyntaja

class VariableDeclaration(varType:Type,varName:String, var assignmentExp: Expression = EmptyExpression, val const : Boolean = false) : Statement {

    val variable = Variable(varName, varType, const)

    override fun writeCode() :String {
        if( variable.type is FunctionPointer ) {
            val fp = variable.type as FunctionPointer
            fp.name = variable.name
            var s = fp.writeFunctionPointer()
            if (!assignmentExp.equals(EmptyExpression)) {
                s += " = " + assignmentExp.writeCode()
            }
            return s
        }
        else {
            var s = writeConst() + variable.type.name + " " + variable.name
            if (!assignmentExp.equals(EmptyExpression)) {
                s += " = " + assignmentExp.writeCode()
            }
            return s
        }
    }

    fun writeDeclaration(): String {
        return writeConst() + variable.type.name + " "+variable.name
        // do not write assignment expression in declaration, even if it would be defined.
    }

    fun writeConst() : String = if (const) "const " else ""
}

