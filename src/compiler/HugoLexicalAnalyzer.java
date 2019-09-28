package compiler;

import java.util.stream.Stream;


public class HugoLexicalAnalyzer {

    public Stream<String> analyze(Stream<String> fileStream) {

        Stream<String> analyzed = fileStream
                                            .map(s -> s.trim().replaceAll(" +", " ")); // Clearing white spaces
        
        return analyzed;
    }

}