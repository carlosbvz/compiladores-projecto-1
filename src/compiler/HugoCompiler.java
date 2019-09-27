package compiler;

import java.util.stream.Stream;

public class HugoCompiler { 

    private String path = "C:\\Program Files (x86)\\MSWLogo\\bc5\\logo32.exe";

    public void compile(String fileName, Stream<String> fileStream, HugoLexicalAnalyzer lexicalAnalyzer, HugoFilesGenerator hugoFilesGenerator) throws Exception {
         
            // Analyze Lexically
            Stream<String> fileStreamLexicallyAnalyzed = lexicalAnalyzer.analyze(fileStream);
            // fileStreamLexicallyAnalyzed.forEach(System.out::println);
            // Generate Error logs

            hugoFilesGenerator.print(fileName, fileStreamLexicallyAnalyzed);

            Process process = new ProcessBuilder(path,"-l", "./cuadro.lgo").start();

            
    }

}