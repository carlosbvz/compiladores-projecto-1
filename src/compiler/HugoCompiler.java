package compiler;

import java.util.ArrayList;
import java.util.stream.Stream;

public class HugoCompiler { 

    public void compile(String fileName, Stream<String> fileStream, HugoLexicalAnalyzer lexicalAnalyzer, HugoFilesGenerator hugoFilesGenerator) throws Exception {
         
            // Analyze Lexically
            ArrayList<String[]> tokensArrayLexicallyAnalyzed = lexicalAnalyzer.analyze(fileStream);


            // This is how you print the tokens...
            // tokensArrayLexicallyAnalyzed.forEach(tokens -> {
            //     System.out.println("Line of Code ============>"+tokens);
            //     for (int x=0; x<tokens.length; x++)
            //         System.out.println(tokens[x]);  
            // });
            
            // TODO: Fix how this is printing the files
            // hugoFilesGenerator.print(fileName, tokensArrayLexicallyAnalyzed);
    }

}