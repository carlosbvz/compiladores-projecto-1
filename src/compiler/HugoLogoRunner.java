package compiler;

import java.io.File;
import java.io.IOException;

public class HugoLogoRunner {

    private String logoProcess = "logo32.exe";

    public void run(String fileName) throws IOException {
        File file = new File("");
        String filePath = file.getAbsolutePath();
        Process process = new ProcessBuilder(logoProcess,"-l", "C:\\Users\\evely_000\\Documents\\compiladores\\compiladores-projecto-1\\data\\cuadro.lgo").start();
    }
}

