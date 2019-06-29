package de.schabby.cyntaja

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*

object gcc {


    fun writeToFileAndCompile(program: Program, pathToGCC: String, sourceFile: String, targetFile: String) {

        program.writeToFile(sourceFile)

        compile(pathToGCC, sourceFile, targetFile)
    }

    fun compile(pathToGCC: String, sourceFile: String, targetFile: String): Int {
        try {
            //String pathToGCC="C:\\Users\\johan\\Downloads\\codeblocks-17.12mingw-nosetup\\MinGW\\bin\\gcc.exe";
            //String sourceFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.c";
            //String targetFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.exe";

            val fullCommand = "$pathToGCC $sourceFile -o $targetFile"
            println("running: "+fullCommand)
            //System.out.println(fullCommand);

            val pb = ProcessBuilder(pathToGCC, sourceFile, "-o", targetFile)

            // set working directory to pathToGCC without the gcc.exe -- WINDOWS
            //pb.directory(new File(pathToGCC.substring(0, pathToGCC.length()-"gcc.exe".length())));

            // Linux
            pb.directory(File("."))

            val p = pb.start()

            //Process p = Runtime.getRuntime().exec("cmd /c dir");
            val brInput = BufferedReader(InputStreamReader(p.inputStream))
            val brError = BufferedReader(InputStreamReader(p.errorStream))

            var line = brInput.readLine()
            while (line != null) {
                println(line)
                //CompilerMessage message = new CompilerMessage(false, line);
                //messages.add(message);
                line = brInput.readLine()
            }

            brInput.close()

            var hasError = false
            line = brError.readLine()
            while (line != null) {
                println("error stream: $line")
                //CompilerMessage message = new CompilerMessage(true, line);
                //messages.add(message);
                hasError = true
                line = brError.readLine()
            }
            brError.close()

            val result = p.waitFor()

            if (hasError) throw RuntimeException("compilation error")

            return result
        } catch (err: Exception) {
            err.printStackTrace()
        }

        return 1
    }
}

fun runExecutable(pathToExecutable:String) : List<String> {

    val pb = ProcessBuilder(pathToExecutable)

    // set working directory to pathToGCC without the gcc.exe -- WINDOWS
    //pb.directory(new File(pathToGCC.substring(0, pathToGCC.length()-"gcc.exe".length())));

    // Linux
    pb.directory(File("."))

    val p = pb.start()

    //Process p = Runtime.getRuntime().exec("cmd /c dir");
    val brInput = BufferedReader(InputStreamReader(p.inputStream))
    val brError = BufferedReader(InputStreamReader(p.errorStream))

    val messages = mutableListOf<String>()
    var line = brInput.readLine()
    var i = 0
    while (line != null) {
        println("line $i: $line")
        i++
        messages.add(line)
        line = brInput.readLine()
    }

    line = brError.readLine()
    var error = false
    while (line != null) {
        println(line)
        error = true
        line = brInput.readLine()
    }

    if(p.exitValue() != 0) throw RuntimeException("exit value was ${p.exitValue()}")

    if(error) throw RuntimeException("There was an error during execution")

    return messages
}