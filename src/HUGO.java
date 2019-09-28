import compiler.HugoCompiler;
import compiler.HugoLexicalAnalyzer;
import compiler.HugoErrorFileGenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HUGO {

    private static String fileName;
    private static HugoCompiler hugoCompiler = new HugoCompiler();
    private static HugoLexicalAnalyzer hugoLexicalAnalyzer = new HugoLexicalAnalyzer();
    private static HugoErrorFileGenerator hugoErrorFileGenerator = new HugoErrorFileGenerator();
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Por favor provea el path del archivo de entrada");
            System.exit(0);
        } else {
            fileName = args[0];
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                hugoCompiler.compile(fileName, stream, hugoLexicalAnalyzer, hugoErrorFileGenerator);
            } catch (Exception e) {
                System.out.println("Hubo un error procesando el archivo de entrada.");
            }
        }
    }
}