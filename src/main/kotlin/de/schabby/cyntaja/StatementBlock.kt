package de.schabby.cyntaja

import de.schabby.cyntaja.tools.malloc

class StatementBlock(val parent:VariableContainer) : Writable {
    val list = mutableListOf<Statement>()
    override fun writeCode(): String {
        val sb = StringBuilder()
        list.forEach {s ->
            sb.append("   ")
            sb.append(s.writeCode())
            sb.append(";\n")
        }
        return sb.toString()
    }

    fun functionCall(name:String, vararg params: Expression) {
        val fb = FunctionCall(name)
        params.forEach {
            fb.parameters.add(it)
        }
        list.add(fb)
    }

    fun returnStatement(exp: Expression) {
        val fb = Return(exp)
        list.add(fb)
    }


    fun functionCall(func: Function, vararg params: Expression) {
        val fb = FunctionCall(func.name)
        params.forEach {
            fb.parameters.add(it)
        }
        list.add(fb)
    }


    fun variableDeclaration(varType:Type, varName:String) : Variable {
        println("in variableDeclaration()")
        val s = VariableDeclaration(varType, varName)
        list.add(s)
        return s.variable
    }

    fun variableDeclaration(varType:Type, varName:String, assignmentExp: Expression) : Variable {
        val s = VariableDeclaration(varType, varName, assignmentExp)
        list.add(s)
        return s.variable
    }

    fun ifStatement(booleanExp: Expression, block:IfStatment.()->Unit) {
        val ifStmt = IfStatment(booleanExp, this)
        ifStmt.apply(block)
        list.add(ifStmt)
    }


    fun assignment(lvalue:LValue, exp: Expression) {
        list.add(AssignmentStatement(lvalue, exp))
    }

    /**
     * Convenience function for a shorthand of
     * <code>MemberAccess(VarIdentifier(var1), VarIdentifier(var2))</code>
     */
    fun fieldAccess(var1:Variable, var2:Variable) : FieldAccess {
        return FieldAccess(VarIdentifier(var1), VarIdentifier(var2))
    }

    fun fieldAccess(var1:Variable, var2:Variable, var3:Variable) : FieldAccess {
        return FieldAccess(FieldAccess(VarIdentifier(var1), VarIdentifier(var2)), VarIdentifier(var3))
    }


    fun findStruct(name:String) : Struct = parent.findStruct(name)

    fun findVar(name:String) : Variable {
        list.forEach { s ->
            if (s is VariableDeclaration && s.variable.name.equals(name)) return s.variable
        }

        return parent.findVar(name)
    }

    /**
     * Declares a pointer variable to a new memory space of a Struct on the heap.
     *
     * Shorthand for <code>variableDeclaration(struct.asPointer, varName, malloc(struct))</code>
     */
    fun structMallocDeclaration(varName:String, struct:Struct) : Variable {
        return variableDeclaration(struct.asPointer, varName, malloc(struct))
    }

    /**
     * shorthand for <code>functionCall("printf", StringLiteral(stringLiteral))</code>
     */
    fun printf(stringLiteral:String) {
        functionCall("printf", StringLiteral(stringLiteral))
    }


    /**
     * shorthand for functionCall("printf", StringLiteral(stringLiteral), exp1)
     */
    fun printf(stringLiteral:String, exp1:Expression) {
        functionCall("printf", StringLiteral(stringLiteral), exp1)
    }

    /**
     * shorthand for functionCall("printf", StringLiteral(stringLiteral), exp1, exp2)
     */
    fun printf(stringLiteral:String, exp1:Expression, exp2:Expression) {
        functionCall("printf", StringLiteral(stringLiteral), exp1, exp2)
    }

}