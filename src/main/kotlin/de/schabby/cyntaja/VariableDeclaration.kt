package de.schabby.cyntaja

class VariableDeclaration(varType:Type,varName:String, val assignmentExp: Expression = emptyExpression) : Statement {

    val variable = Variable(varName, varType)

    override fun writeCode() :String {
        var s = variable.type.name + " "+variable.name
                if ( !assignmentExp.equals(emptyExpression) ) {
            s += " = " + assignmentExp.writeCode()
        }
        return s
    }
}
