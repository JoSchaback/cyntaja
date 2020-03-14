package de.schabby.cyntaja.tools

import de.schabby.cyntaja.*
import java.lang.RuntimeException

/**
 * Returns malloc() Expression for memory allocation. With 'foo' being a Struct type, the method
 * returns a shorthand for <code>(struct foo *) malloc(sizeof(struct foo));</code>
 */
fun malloc(type: ValueType, time:Int) : Expression {
    if( time < 1) throw RuntimeException("parameter 'time' can not be less than 1 (it is $time)")
    val malloc = FunctionCallLibrary("malloc")
    if( time >1 ) {
        malloc.parameters.add(BinaryOperator(Sizeof(type), "*", Literal(time.toString())))
    } else {
        malloc.parameters.add(Sizeof(type))
    }

    return Casting(type.asPointer, malloc)
}

fun malloc(type: ValueType) : Expression  = malloc(type, 1)


