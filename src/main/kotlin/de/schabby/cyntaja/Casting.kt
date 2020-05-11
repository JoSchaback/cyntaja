package de.schabby.cyntaja

class Casting (var type:Type, var exp:Expression) : Expression {

    override fun writeCode(): String {
        if( type is FunctionPointer ) {
            val fp = type as FunctionPointer
            return "(${fp.writeFunctionPointer()}) "+exp.writeCode()
        }
        else return "(${type.name}) "+exp.writeCode()
    }
}