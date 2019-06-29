# Cyntaja
Syntax Tree builder for the C programming language in Kotlin.

For example, building the syntax tree of a simple "Hello, World" example looks like that in Cyntaja:
```kotlin
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
```
The code above generates C code like this
```c
#include<stdio.h>
#include<stdint.h>
int32_t main() {
   printf("Hello, World!");
   return 0;
}
```