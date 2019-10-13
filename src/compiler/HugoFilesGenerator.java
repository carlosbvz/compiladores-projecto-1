package compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HugoFilesGenerator {

    private Integer count = 0;

    // We asume lines are not longer than 999
    private String getCounterLine(Integer count) {
        if (count < 10) return "00"+count;
        else if (count < 999) return "0"+count;
        else return count.toString();
    }

    void print(String fileName, ArrayList<String[]> stream) throws IOException {
        final String errorFileName = fileName.substring(0, fileName.lastIndexOf('.')) + "-HUGO-errores.txt";
        final File errorFile = new File("./", errorFileName);
        BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile));

        final String logoFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".lgo";
        final File logoFile = new File("./", logoFileName);
        BufferedWriter logoWriter = new BufferedWriter(new FileWriter(logoFile));
        
        stream.forEach(s -> {
            try {
                String errorLine = new String(getCounterLine(++count) + " " + s + System.lineSeparator());
                errorWriter.write(errorLine);
                logoWriter.write(s +  System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        
        errorWriter.close();
        errorFile.createNewFile();
        logoWriter.close();
        logoFile.createNewFile();
    }

}