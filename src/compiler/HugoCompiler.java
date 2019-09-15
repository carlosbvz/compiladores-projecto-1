package compiler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class HugoCompiler {

    

    private void GenerateErrorFile(String fileName, String fileContent) throws Exception {
        final File file = new File("./", fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(fileContent);
        writer.close();
        file.createNewFile();
    }

    public void compile(String fileName) throws Exception {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
            // TODO: Analyze each line Syntax and Semantic
            // TODO: If there are errors, return error file
            
            final String errorFileName = fileName.substring(0, fileName.lastIndexOf('.')) + "-HUGO-errores.txt";
            GenerateErrorFile(errorFileName, "File content ");
        } catch (Exception e) {
            System.out.println("Hubo un error con el archivo de entrada.");
        }

    }

}