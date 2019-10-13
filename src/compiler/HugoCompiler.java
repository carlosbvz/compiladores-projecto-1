package compiler;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class HugoCompiler {
    
    private Boolean areThereErrorLogs(ArrayList<String> errorLogs) {
        // !nullsOnly
        return !errorLogs.stream().noneMatch(Objects::nonNull);
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

            if (areThereErrorLogs(errorLogs)) {
                // TODO: Fix how this is printing the files
                System.out.println("Hay errores en el código fuente");

                // Logging errors
                errorLogs.forEach(error -> {
                    System.out.println(error);
                });
                hugoFilesGenerator.print(fileName, tokensArrayLexicallyAnalyzed);
            } else {
                // TODO: print logo file
                // Runs Logo 
                System.out.println("No hay errores en el código fuente");
                if (shouldRunLogo) logoRunner.run(fileName);
            }
    }

}