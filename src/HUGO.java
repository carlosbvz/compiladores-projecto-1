import compiler.HugoCompiler;
import compiler.HugoLexicalAnalyzer;
import compiler.HugoLogoRunner;
import compiler.HugoSyntaxAnalyzer;
import compiler.HugoFilesGenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HUGO {

    private static String fileName;
    private static HugoCompiler hugoCompiler = new HugoCompiler();
    private static HugoLexicalAnalyzer hugoLexicalAnalyzer = new HugoLexicalAnalyzer();
    private static HugoSyntaxAnalyzer hugoSyntaxAnalyzer = new HugoSyntaxAnalyzer();
    private static HugoFilesGenerator hugoFilesGenerator = new HugoFilesGenerator();
    private static HugoLogoRunner hugoLogoRunner = new HugoLogoRunner();
    private static Boolean shouldRunLogo = false; // If set to 'false', forces the System from running the logo app
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Por favor provea el path del archivo de entrada");
            System.exit(0);
        } else {
            fileName = args[0];
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                hugoCompiler.compile(fileName, stream, hugoLexicalAnalyzer, hugoFilesGenerator, hugoSyntaxAnalyzer, hugoLogoRunner, shouldRunLogo);
            } catch (Exception e) {
                System.out.println("Hubo un error procesando el archivo de entrada.");
            }
        }
    }
}