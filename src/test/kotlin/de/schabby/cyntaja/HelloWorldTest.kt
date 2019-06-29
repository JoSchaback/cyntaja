package de.schabby.cyntaja

import de.schabby.cyntaja.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HelloWorldTest {

    @Test
    @DisplayName("compile and run HelloWorld.c")
    fun helloWorld() {

        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)

            function("main") {
                returnType = Type.int
                body {
                    functionCall("printf", StringLiteral("Hello, World!"))
                    returnStatement(Literal("0"))
                }
            }
        }

        gcc.writeToFileAndCompile(p, "gcc", "build/helloworld.c", "build/helloworld")
        val lines = runExecutable("build/helloworld")

        assertThat(lines[0]).isEqualTo("Hello, World!")
    }

}