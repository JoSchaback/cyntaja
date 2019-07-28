package de.schabby.cyntaja

import java.io.File


class Program(var importStd:Boolean = false) {

    val functions  = mutableListOf<Function>()
    val structs    = mutableListOf<Struct>()
    val includes   = mutableListOf<Include>()
    val globalVars = mutableListOf<VariableDeclaration>()

    fun writeToFile(filename:String) {

        val pwd = File(filename).printWriter()

        pwd.println("// this is an automatically generated file. Do not edit.")

        includes.forEach {
            pwd.println(it.writeCode())
        }
        pwd.println("// ====[ Structs ]====")
        structs.forEach {
            pwd.println(it.writeCode())
        }
        pwd.println("// ====[ Functions Declarations ]====")
        functions.forEach {
            pwd.println(it.writeDeclaration())
        }
        pwd.println("// ====[ Global Vars ]====")
        globalVars.forEach {
            pwd.println(it.writeCode()+";")
        }
        pwd.println("// ====[ Functions ]====")
        functions.forEach {
            pwd.println(it.writeCode())
        }
        pwd.println("// end of file")
        pwd.close()

    }

    fun function(name:String, block: Function.()->Unit) : Function {
        val f = Function(name, this)
        f.block()
        functions.add(f)
        return f
    }

    fun struct(name:String, block:Struct.()->Unit) : Struct {
        val s = Struct(name)
        s.apply(block)
        structs.add(s)
        return s
    }

    fun globalVar(varType:Type, varName:String, exp:Expression) : Variable {
        val s = VariableDeclaration(varType, varName, exp)
        globalVars.add(s)
        return s.variable
    }

    fun globalVar(varType:Type, varName:String) : Variable {
        val s = VariableDeclaration(varType, varName)
        globalVars.add(s)
        return s.variable
    }

    fun include(lib:String) {
        include(Include(lib))
    }
    fun include(lib:Include) {
        includes.add(lib)
    }

    fun findStruct(name: String): Struct {
        val result = structs.filter { s -> s.name.equals(name) }
        if (result.isEmpty()) throw RuntimeException("could not find Struct $name in Program")
        return result.first()
    }

    fun build(block:Program.()->Unit) : Program {
        this.apply(block)
        return this
    }
}