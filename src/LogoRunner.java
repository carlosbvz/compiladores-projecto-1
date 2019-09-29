import java.io.File;
import java.io.IOException;

class LogoRunner {

    private String logoProcess = "logo32.exe";

    void run(String fileName) throws IOException {

        File file = new File("");
        String filePath = file.getAbsolutePath();
        Process process = new ProcessBuilder(logoProcess,"-l", "C:\\Users\\evely_000\\Documents\\compiladores\\compiladores-projecto-1\\data\\cuadro.lgo").start();
    }
}

