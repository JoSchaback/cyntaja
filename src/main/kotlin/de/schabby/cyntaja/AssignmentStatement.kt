package de.schabby.cyntaja

class AssignmentStatement(val lvalue: LValue, var exp: Expression) :
    Statement {
    override fun writeCode() = "${lvalue.writeCode()} = "+exp.writeCode()

}