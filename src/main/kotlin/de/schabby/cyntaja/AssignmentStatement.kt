package de.schabby.cyntaja

class AssignmentStatement(val lvalue: LValue, val exp: Expression) :
    Statement {
    override fun writeCode() = "${lvalue.writeCode()} = "+exp.writeCode()

}