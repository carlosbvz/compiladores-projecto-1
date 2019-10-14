package compiler;

import java.util.ArrayList;
import java.util.stream.Stream;

public class HugoCompiler {

    private Boolean areThereErrorOnLogs(ArrayList<String> errorLogs) {

        for (int index = 0; index < errorLogs.size(); index++) {
            String lineOfCode = errorLogs.get(index);
            String joinedString = String.join("", lineOfCode);
            if (joinedString.contains("ERROR")) {
                return true;
            }
        }
        return false;
    }

    public void compile(String fileName, 
                        Stream<String> fileStream, 
                        HugoLexicalAnalyzer lexicalAnalyzer, 
                        HugoFilesGenerator hugoFilesGenerator, 
                        HugoSyntaxAnalyzer hugoSyntaxAnalyzer,
                        HugoLogoRunner logoRunner,
                        Boolean shouldRunLogo) throws Exception {
         

            /**
             * Analyze Lexically
             *  - Creates a set of tokens to be used for the SyntaxAnalyzer
             *  - It can be considered a 'clean' data structure (no spaces, comments, etc...)
             *  - Will be used to print .logo file
             */
            ArrayList<String[]> tokensArrayLexicallyAnalyzed = lexicalAnalyzer.analyze(fileStream);

            /**
             * Analyze Syntactically
             *  - Uses the 'clean' data structure from the Lexical Analizys.
             *  - It will find & flag errors (if any).
             */
            ArrayList<String> errorLogs = hugoSyntaxAnalyzer.analyze(tokensArrayLexicallyAnalyzed);

            // Logging errors
            // errorLogs.forEach(error -> {
            //     System.out.print("Linea de error => ");
            //     System.out.println(error);
            // });
            if (areThereErrorOnLogs(errorLogs)) {
                hugoFilesGenerator.printErrorsFile(fileName, errorLogs, tokensArrayLexicallyAnalyzed);
                System.out.println("Error de Compilación. Por favor revise el archivo de errores para más información.");
            } else {
                hugoFilesGenerator.printLogoFile(fileName, tokensArrayLexicallyAnalyzed);
                System.out.println("Compilación exitosa");
                if (shouldRunLogo) logoRunner.run(fileName);
            }
    }

}