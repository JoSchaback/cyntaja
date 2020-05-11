package de.schabby.cyntaja

import de.schabby.cyntaja.tools.malloc
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FunctionPointerTest {

    @Test
    fun funcpointerTest() {

        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)
            include(Include.STDLIB)

            function("main") {
                returnType = Type.i32
                body {
                    val intPtr = variableDeclaration(FunctionPointer(Type.i32, Type.i32, Type.i32), "func_ptr")
                    functionCall("printf", StringLiteral("address %p\\n"), VarIdentifier(intPtr))
                    returnStatement(Literal("0"))
                }
            }
        }

        gcc.writeToFileAndCompile(p, "gcc", "build/funcp.c", "build/funcp")
        val lines = runExecutable("build/funcp")

        //Assertions.assertThat(lines[0].startsWith("address "))
    }
}