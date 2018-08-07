package de.schabby.cyntaja.tools;

import de.schabby.cyntaja.Index;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompileExecutor {


    public static int compile(String pathToGCC, String sourceFile, String targetFile) {
        try {
            String line;
            //String pathToGCC="C:\\Users\\johan\\Downloads\\codeblocks-17.12mingw-nosetup\\MinGW\\bin\\gcc.exe";
            //String sourceFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.c";
            //String targetFile = "C:\\Users\\johan\\git\\cyntaja\\target\\main.exe";

            String fullCommand = pathToGCC+" "+sourceFile+" -o "+targetFile;
            System.out.println(fullCommand);

            ProcessBuilder pb = new ProcessBuilder(pathToGCC, sourceFile, "-o", targetFile);

            // set working directory to pathToGCC without the gcc.exe
            pb.directory(new File(pathToGCC.substring(0, pathToGCC.length()-"gcc.exe".length())));

            Process p = pb.start();

            //Process p = Runtime.getRuntime().exec("cmd /c dir");
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            List<CompilerMessage> messages = new ArrayList<>();

            while ((line = bri.readLine()) != null)
            {
                System.out.println("input stream: "+line);
                CompilerMessage message = new CompilerMessage(false, line);
                messages.add(message);
            }

            bri.close();

            while ((line = bre.readLine()) != null)
            {
                System.out.println("error stream: "+line);
                CompilerMessage message = new CompilerMessage(true, line);
                messages.add(message);
            }
            bre.close();

            int result = p.waitFor();

            return result;
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        return 1;
    }



}
