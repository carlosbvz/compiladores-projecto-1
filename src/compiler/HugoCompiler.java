package compiler;

import java.util.stream.Stream;

public class HugoCompiler {


    public void compile(String fileName, Stream<String> fileStream, HugoLexicalAnalyzer lexicalAnalyzer, HugoErrorFileGenerator errorFileGenerator) throws Exception {
        
            // Analyze Lexically
            Stream<String> fileStreamLexicallyAnalyzed = lexicalAnalyzer.analyze(fileStream);            
            errorFileGenerator.print(fileName, fileStreamLexicallyAnalyzed);


    }

}