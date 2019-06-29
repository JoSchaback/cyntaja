package de.schabby.cyntaja

class BinaryOperator(val left: Expression, val operator:String, val right: Expression) :
    Expression {

    override fun writeCode() = left.writeCode() + " "+operator+" "+right.writeCode()

}