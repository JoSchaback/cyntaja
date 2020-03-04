package de.schabby.cyntaja

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ArrayInitializerTest {

    @Test
    @DisplayName("compile and run ArrayInitializerTest.c")
    fun arrayInitializerTest() {
        // double balance[] = {1000.0, 2.0, 3.4, 7.0, 50.0};

        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)

            function("main") {
                returnType = Type.i32
                body {
                    val some_array = arrayDeclaration(Type.i32, "some_array", Literal("{5, 2, 7, 5, 4, 3, 1, 2, 3, 3}"));
                    functionCall("printf", StringLiteral("Array: %i, %i, %i"),
                        ArraySubscript(some_array, Literal("0")),
                        ArraySubscript(some_array, Literal("1")),
                        ArraySubscript(some_array, Literal("2")))
                    returnStatement(Literal("0"))
                }
            }
        }

        gcc.writeToFileAndCompile(p, "gcc", "build/array_initializer.c", "build/array_initializer")
        val lines = runExecutable("build/array_initializer")

        Assertions.assertThat(lines[0]).isEqualTo("Array: 5, 2, 7")
    }

    @Test
    @DisplayName("compile and run globalArrayInitializerTest.c")
    fun globalArrayInitializerTest() {
        // double balance[] = {1000.0, 2.0, 3.4, 7.0, 50.0};

        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)

            val string = struct("String") {
                field("length", Type.u16)
                field("charArray", Type.char.asPointer)
            }

            globalArray(Type.char, "global_char_array_1", Literal("{65, 66, 67, 68, 69, 70, 0}"));
            globalArray(Type.char, "global_char_array_2", Literal("{85, 77, 67, 69, 86, 0}"));
            val globalString = globalVar(string, "global_string_1", Literal("{7, global_char_array_1}"), /* const */ true);

            function("main") {
                returnType = Type.i32
                body {
                    functionCall("printf", StringLiteral("Array: %s"), fieldAccess(globalString, string.field("charArray")))
                    returnStatement(Literal("0"))
                }
            }
        }

        gcc.writeToFileAndCompile(p, "gcc", "build/globalArrayInitializerTest.c", "build/globalArrayInitializerTest")
        val lines = runExecutable("build/globalArrayInitializerTest")

        Assertions.assertThat(lines[0]).isEqualTo("Array: ABCDEF")
    }

}