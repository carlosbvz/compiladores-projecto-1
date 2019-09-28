package compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class HugoErrorFileGenerator {

    void print(String fileName, Stream<String> stream) throws IOException {
        final String errorFileName = fileName.substring(0, fileName.lastIndexOf('.')) + "-HUGO-errores.txt";
        final File file = new File("./", errorFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("File Content Hugo");
        writer.close();
        file.createNewFile();
    }

}