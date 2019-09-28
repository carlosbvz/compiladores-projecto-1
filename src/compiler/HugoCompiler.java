package compiler;

import java.util.stream.Stream;

public class HugoCompiler {


    public void compile(String fileName, Stream<String> fileStream, HugoLexicalAnalyzer lexicalAnalyzer, HugoFilesGenerator hugoFilesGenerator) throws Exception {
         
            // Analyze Lexically
            Stream<String> fileStreamLexicallyAnalyzed = lexicalAnalyzer.analyze(fileStream);
            // fileStreamLexicallyAnalyzed.forEach(System.out::println);
            // Generate Error logs

            hugoFilesGenerator.print(fileName, fileStreamLexicallyAnalyzed);

            
    }

}