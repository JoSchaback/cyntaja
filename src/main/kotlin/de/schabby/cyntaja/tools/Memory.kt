package de.schabby.cyntaja.tools

import de.schabby.cyntaja.*
import java.lang.RuntimeException

/**
 * returns Expression for memory allocation.
 * (struct foo *) malloc(sizeof(struct foo));
 */
fun malloc(type: Type, time:Int) : Expression {
    if(type.isPointer) throw RuntimeException("type shall not be pointer")
    val malloc = FunctionCall("malloc")
    malloc.parameters.add(BinaryOperator(Sizeof(type), "*", Literal(time.toString())))
    return Casting(Type(type.name+"*", isPointer = true), malloc)
}

fun malloc(type: Type) : Expression  = malloc(type, 1)


fun malloc(type: Type, time:Variable) : Expression {
    if(type.isPointer) throw RuntimeException("type shall not be pointer")
    val malloc = FunctionCall("malloc")
    malloc.parameters.add(BinaryOperator(Sizeof(type), "*", VarIdentifier(time)))
    return Casting(Type(type.name+"*", isPointer = true), malloc)
}
