package compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class HugoErrorFileGenerator {

    private Integer count = 0;

    private String getCounterLine(Integer count) {
        if (count < 10) return "00"+count;
        else return "0"+count;
    }

    void print(String fileName, Stream<String> stream) throws IOException {
        final String errorFileName = fileName.substring(0, fileName.lastIndexOf('.')) + "-HUGO-errores.txt";
        final File file = new File("./", errorFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        stream.forEach(s -> {
            try {
                writer.write(getCounterLine(++count) + " " + s+ "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        });
        writer.close();
        file.createNewFile();
    }

}