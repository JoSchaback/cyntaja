package de.schabby.cyntaja

/**
 * Variable holds a Type and name. Use VariableDeclaration to declare Variables. Use VarIdentifier to
 * access a variable
 */
open class Variable(var name:String, var type:Type, val const:Boolean = false) {

}