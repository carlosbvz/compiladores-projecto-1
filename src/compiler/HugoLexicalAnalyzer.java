package compiler;

import java.util.stream.Stream;


public class HugoLexicalAnalyzer {

    private Stream<String> clearSpaces(Stream<String> fileStream) {
        // fileStream.forEach(System.out::println);
        
        return fileStream;
    }

    public Stream<String> analyze(Stream<String> fileStream) {
        Stream<String> fileStreamWithWhiteSpacesCleaned = clearSpaces(fileStream);
        fileStreamWithWhiteSpacesCleaned.forEach(System.out::println);
        return fileStreamWithWhiteSpacesCleaned;
    }

}