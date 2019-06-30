package de.schabby.cyntaja


import org.junit.jupiter.api.Test

class StructTest {

    @Test
    fun structTest() {
        val p = Program().build {
            include(Include.STDIO)
            include(Include.STDINT)
            include(Include.STDLIB)

            val date = struct("Date") {
                field("year", Type.uint)
                field("month", Type.uint)
                field("day_in_month", Type.uint)
            }

            val string = struct("String") {
                field("chars", Type.char.asPointer)
                field("length", Type.uint)
            }

            val author = struct("Author") {
                field("firstName", string)
                field("lastName", string)
                field("date_of_birth", date)
            }

            val book = struct("Book") {
                field("name", string)
                field("ISBN", string)
                field("price", Type.float)
            }

            function("main") {
                returnType = Type.int
                body {
                    structMallocDeclaration("b1", book)
                    printf("hello, this is a test")
                }
            }

        }
        val execname = "structtest"
        gcc.writeToFileAndCompile(p, "gcc", "build/$execname.c", "build/$execname")
        val lines = runExecutable("build/$execname")
    }
}