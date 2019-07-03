package de.schabby.cyntaja

import de.schabby.cyntaja.Type.Companion.char
import de.schabby.cyntaja.Type.Companion.int
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MoreSophisticatedTest {

    @Test
    fun aMoreSophisticatedTest() {
        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)

            val book = struct("Book") {
                field("name", char.asPointer)
                field("other", int.asPointer)
                field("great", int)
            }

            val helloWorld = function("helloWorld") {
                parameters {
                    variable("i", int)
                    variable("j", int.asPointer)
                }
                body {
                    variableDeclaration(Type.int, "someVar")
                    functionCall("printf", StringLiteral("Hello, World %f %f !!\\n"), Literal("34.43"), Literal("-0.004"))
                    functionCall("printf", StringLiteral("compute this %d\\n"),
                        BinaryOperator(Literal("12"), "+", Literal("13"))
                    )
                    assignment(VarIdentifier(findVar("i")),
                        BinaryOperator(Literal("12"), "+", Literal("13"))
                    )
                    variableDeclaration(Type.int, "otherVar", Literal("0"))
                    val bookVar = variableDeclaration(book, "book")
                    ifStatement(BinaryOperator(Literal("4"), "<", Literal("5"))) {
                        then {
                            functionCall("printf", StringLiteral("Hi, this is the 'then' block\\n"))
                        }
                        otherwise {
                            functionCall("printf", StringLiteral("Hi, this is the 'else' block\\n"))
                        }
                    }
                    assignment(FieldAccess(VarIdentifier(bookVar), VarIdentifier(book.fields[2])), BinaryOperator(Literal("12"), "+", Literal("13")))
                    //assignment(FieldAccess(VarIdentifier(bookVar),ArraySubscript(book.fields[0], Literal("3"))), CharLiteral('l'))
                }
            }
            function("main") {
                returnType = Type.int
                body {
                    functionCall(helloWorld, Literal("120"), NULL)
                }
            }


        }

        gcc.writeToFileAndCompile(p, "gcc", "build/soph.c", "build/soph")
        val lines = runExecutable("build/soph")

        assertThat(lines[0]).isEqualTo("Hello, World 34.430000 -0.004000 !!")

    }

}