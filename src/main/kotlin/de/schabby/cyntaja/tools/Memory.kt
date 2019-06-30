package de.schabby.cyntaja.tools

import de.schabby.cyntaja.*
import java.lang.RuntimeException

/**
 * returns Expression for memory allocation.
 * (struct foo *) malloc(sizeof(struct foo));
 */
fun malloc(type: ValueType, time:Int) : Expression {
    val malloc = FunctionCall("malloc")
    malloc.parameters.add(BinaryOperator(Sizeof(type), "*", Literal(time.toString())))
    return Casting(type.asPointer, malloc)
}

fun malloc(type: ValueType) : Expression  = malloc(type, 1)


fun malloc(type: ValueType, time:Variable) : Expression {
    val malloc = FunctionCall("malloc")
    malloc.parameters.add(BinaryOperator(Sizeof(type), "*", VarIdentifier(time)))
    return Casting(type.asPointer, malloc)
}
