package compiler;

import java.util.stream.Stream;
import java.util.ArrayList;

public class HugoLexicalAnalyzer {

    private ArrayList<String[]> getTokensListArray(Stream<String> streamCleanedSpaced) {

        ArrayList<String[]> tokensListArray = new ArrayList<String[]>();

        /**
         * Removes empty lines
         * Removes comments
         * Standarizes to Lowercase
         * Creates set of tokens
         */
        streamCleanedSpaced.forEach((lineOfCode) -> {
            if(lineOfCode != null && !lineOfCode.isEmpty()) {
                String lineOfCodeWithNoComments = lineOfCode.split(";")[0];
                if(lineOfCodeWithNoComments != null && !lineOfCodeWithNoComments.isEmpty()) {
                    String[] tokens = lineOfCodeWithNoComments.toLowerCase().split("\\s");
                    tokensListArray.add(tokens);
                }
            }
         });

         return tokensListArray;
    }

    public ArrayList<String[]> analyze(Stream<String> fileStream) {

        // Clearing white spaces
        Stream<String> streamCleanedSpaced = fileStream.map(s -> s.trim().replaceAll(" +", " "));
        // Create clean tokens
        ArrayList<String[]> tokensListArray = getTokensListArray(streamCleanedSpaced);

        return tokensListArray;
    }

}