package de.schabby.cyntaja

/**
 * Interface that defines methods such that Variables and Structs can be resolved
 * in an upwards manner in the syntax tree
 */
interface VariableContainer {
    fun findStruct(name:String) : Struct
    fun findVar(name:String) : Variable
}