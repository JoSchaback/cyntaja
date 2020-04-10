package de.schabby.cyntaja

class UnaryOperator(val exp: Expression, val operator:String, val suffix:Boolean) : Expression {

    override fun writeCode() = (if(!suffix) operator else "") + exp.writeCode()+(if(suffix) operator else "")

}