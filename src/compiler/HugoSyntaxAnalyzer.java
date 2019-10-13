package compiler;

import java.util.ArrayList;

public class HugoSyntaxAnalyzer {

    private static ArrayList<String> errorsListArray = new ArrayList<String>();
    private static String[] hugoReservedWords = {"AV", "AVANZA", "RE","RETROCEDE","GD","GIRADERECHA","GI", "GIRAIZQUIERDA"};
    private static String[] logoReservedWords = {"AV", "AVANZA", "RE","RETROCEDE","GD","GIRADERECHA","GI", "GIRAIZQUIERDA"};


    private String analyzeTokensGeneralStructure(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {

        String openWord = "para";
        String closingWord = "fin";
        Boolean hasOpenWord = false;
        Boolean hasClosingWord = false;

        for (int lineIndex = 0; lineIndex < tokensArrayLexicallyAnalyzed.size(); lineIndex++) {
            for (int tokenIndex = 0; tokenIndex < tokensArrayLexicallyAnalyzed.get(lineIndex).length; tokenIndex++) {
                String token = tokensArrayLexicallyAnalyzed.get(lineIndex)[tokenIndex];
                if (token.equals(openWord)) hasOpenWord = true;
                else if (token.equals(closingWord)) hasClosingWord = true;
            }
        }
        // Specific Analysis, General 'fin/para' structure
        if (!hasOpenWord && !hasClosingWord) return "ERROR 000: El código fuente no contiene palabras reservadas 'para' ni 'fin'.";
        else if (!hasOpenWord) return "ERROR 000: El código fuente no contiene palabra reservada 'para'.";
        else if (!hasClosingWord) return "ERROR 000: El código fuente no contiene palabra reservada 'fin'.";
        return null;
    }

    private String analyzeTokensInLineOfCode(String[] tokensInLineOfCode, int lineIndex, int linesCount) {
        
        String lineOfCodeErrorMessage = "";
        // Check every token
        for (int tokenIndex = 0; tokenIndex < tokensInLineOfCode.length; tokenIndex++) {
            String token = tokensInLineOfCode[tokenIndex];
            Boolean isTokenHugoWord = false;
            Boolean isTokenLogoWord = false;
            // Check if is an Hugo reserved Word
            for (int reservedWordIndex = 0; reservedWordIndex < hugoReservedWords.length; reservedWordIndex++) {
                if (token.equals(hugoReservedWords[reservedWordIndex])) {
                    isTokenHugoWord = true;
                    break;
                }
            }
            // Check if is Logo Reserved word
            if (!isTokenHugoWord) {
                for (int reservedWordIndex = 0; reservedWordIndex < logoReservedWords.length; reservedWordIndex++) {
                    if (token.equals(logoReservedWords[reservedWordIndex])) {
                        isTokenLogoWord = true;
                        break;
                    }
                }
            }
        }
        return lineOfCodeErrorMessage;
    }

    public ArrayList<String> analyze(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {


        // Checks for basic general structure 'para/fin' tokens words present in code
        String generalStructureErrorMessage = analyzeTokensGeneralStructure(tokensArrayLexicallyAnalyzed);
        if (generalStructureErrorMessage != null) errorsListArray.add(generalStructureErrorMessage);

        // General Analysis, line by line
        for (int lineIndex = 0; lineIndex < tokensArrayLexicallyAnalyzed.size(); lineIndex++) {
            // errorsListArray.add(analyzeTokensInLineOfCode(tokensArrayLexicallyAnalyzed.get(lineIndex), lineIndex, tokensArrayLexicallyAnalyzed.size() - 1 ));
        }

        return errorsListArray;

    }

}