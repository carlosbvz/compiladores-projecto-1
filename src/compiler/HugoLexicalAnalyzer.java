package compiler;

import java.util.stream.Stream;
import java.util.ArrayList;

public class HugoLexicalAnalyzer {

    private ArrayList<String[]> setTokensArray(Stream<String> streamCleanedSpaced) {

        ArrayList<String[]> tokensArray = new ArrayList<String[]>();//creating new generic arraylist

        streamCleanedSpaced.forEach((lineOfCode) -> {
            String[] tokens = lineOfCode.split("\\s");
            tokensArray.add(tokens);
         });

         return tokensArray;
    }

    public ArrayList<String[]> analyze(Stream<String> fileStream) {

        // Clearing white spaces
        Stream<String> streamCleanedSpaced = fileStream.map(s -> s.trim().replaceAll(" +", " "));
        // Create tokens
        ArrayList<String[]> tokensArray = setTokensArray(streamCleanedSpaced);

        return tokensArray;
    }

}