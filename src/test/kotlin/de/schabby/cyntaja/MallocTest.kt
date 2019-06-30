package de.schabby.cyntaja

import de.schabby.cyntaja.Type.Companion.int
import de.schabby.cyntaja.tools.malloc
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MallocTest {

    @Test
    @DisplayName("compile and run HelloWorld.c")
    fun helloWorld() {

        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)
            include(Include.STDLIB)

            function("main") {
                returnType = Type.int
                body {
                    variableDeclaration(int.asPointer, "intPtr", malloc(int, 100))
                    functionCall("printf", StringLiteral("address %p"), VarIdentifier(findVar("intPtr")))
                    returnStatement(Literal("0"))
                }
            }
        }

        gcc.writeToFileAndCompile(p, "gcc", "build/malloc.c", "build/malloc")
        val lines = runExecutable("build/malloc")

        Assertions.assertThat(lines[0].startsWith("address "))
    }


}