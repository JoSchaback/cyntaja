package de.schabby.cyntaja


import de.schabby.cyntaja.tools.malloc
import org.junit.jupiter.api.Test

class StructTest {

    @Test
    fun structTest() {
        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)
            include(Include.STDLIB)

            val date = struct("Date") {
                field("year", Type.u32)
                field("month", Type.u32)
                field("day_in_month", Type.u32)
            }

            val string = struct("String") {
                field("chars", Type.char.asPointer)
                field("length", Type.u32)
            }

            val author = struct("Author") {
                field("firstName", string.asPointer)
                field("lastName", string.asPointer)
                field("date_of_birth", date.asPointer)
            }

            val book = struct("Book") {
                field("name", string.asPointer)
                field("ISBN", string.asPointer)
                field("publication_date", date.asPointer)
                field("price", Type.float)
            }

            function("print_date") {
                parameters {
                    variable("date_ptr", date.asPointer)
                }
                body {
                    val datePtr = findVar("date_ptr")
                    functionCall("printf", StringLiteral("%i-%i-%i"),
                        fieldAccess(datePtr, date.field("year")),
                        fieldAccess(datePtr, date.field("month")),
                        fieldAccess(datePtr, date.field("day_in_month")))
                }
            }

            function("main") {
                returnType = Type.u32
                body {
                    printf("hello, this is a test\\n")
                    val b1 = structMallocDeclaration("b1", book)
                    assignment(fieldAccess(b1, book.field("name")), malloc(string))
                    assignment(fieldAccess(b1, book.field("name"),string.field("chars")), StringLiteral("The Hitchhiker's Guide to the Galaxy"))
                    assignment(fieldAccess(b1, book.field("name"),string.field("length")), Literal("The Hitchhiker's Guide to the Galaxy".length.toString()))
                    printf("step 2\\n")
                    val adams = structMallocDeclaration("douglasAdams", author)
                    assignment(fieldAccess(adams, author.field("firstName")), malloc(string))
                    assignment(fieldAccess(adams, author.field("firstName"),string.field("chars")), StringLiteral("Douglas"))
                    assignment(fieldAccess(adams, author.field("lastName")), malloc(string))
                    assignment(fieldAccess(adams, author.field("lastName"),string.field("chars")), StringLiteral("Adams"))
                    assignment(fieldAccess(adams, author.field("date_of_birth")), malloc(date))
                    assignment(fieldAccess(adams, author.field("date_of_birth"),date.field("year")), Literal("1952"))
                    assignment(fieldAccess(adams, author.field("date_of_birth"),date.field("month")), Literal("3"))
                    assignment(fieldAccess(adams, author.field("date_of_birth"),date.field("day_in_month")), Literal("11"))
                    printf("now we test structs on the stack, instead of heap \\n")
                    val tolkien = variableDeclaration(author, "tolkien")
                    assignment(fieldAccess(tolkien, author.field("firstName")), malloc(string))
                    assignment(fieldAccess(tolkien, author.field("lastName")), malloc(string))
                    assignment(fieldAccess(tolkien, author.field("date_of_birth")), malloc(date))
                    assignment(fieldAccess(tolkien, author.field("firstName"), string.field("chars")), StringLiteral("John Ronald Reuel"))
                    assignment(fieldAccess(tolkien, author.field("lastName"), string.field("chars")), StringLiteral("Tolkien"))
                    assignment(fieldAccess(tolkien, author.field("date_of_birth"), date.field("year")), Literal.fromInt(1982))
                    assignment(fieldAccess(tolkien, author.field("date_of_birth"), date.field("month")), Literal.fromInt(1))
                    assignment(fieldAccess(tolkien, author.field("date_of_birth"), date.field("day_in_month")), Literal.fromInt(3))
                    printf("Douglas %s was born on ", fieldAccess(adams, author.field("lastName"), string.field("chars")))
                    functionCall("print_date", fieldAccess(adams, author.field("date_of_birth")))
                    printf("\\n")
                    printf("J. R. R. Tolkien was born on ")
                    functionCall("print_date", fieldAccess(adams, author.field("date_of_birth")))
                    printf("\\n")
                    printf("reached the end!\\n")
                    returnStatement(Literal("0"))
                }
            }

        }
        val execname = "structtest"
        gcc.writeToFileAndCompile(p, "gcc", "build/$execname.c", "build/$execname")
        /*val lines = */runExecutable("build/$execname")
    }
}