package compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;


public class HugoLogoFileGenerator {

    void print(String fileName, Stream<String> stream) throws IOException {

        final String logoFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".lgo";
        final File file = new File("./", logoFileName);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        stream.forEach(s -> {
            try {
                writer.write(s+ "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
        file.createNewFile();
    }

}