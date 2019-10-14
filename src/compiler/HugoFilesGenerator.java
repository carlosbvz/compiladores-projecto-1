package compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HugoFilesGenerator {

    private Integer count = 0;

    // We asume lines are not longer than 999
    private String getCounterLine(Integer count) {
        if (count < 10) return "00"+count;
        else if (count < 999) return "0"+count;
        else return count.toString();
    }

    public void printLogoFile(String fileName, ArrayList<String[]> tokensArrayLexicallyAnalyzed) throws IOException {
        final String logoFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".lgo";
        final File logoFile = new File("./", logoFileName);
        BufferedWriter logoWriter = new BufferedWriter(new FileWriter(logoFile));

        tokensArrayLexicallyAnalyzed.forEach(lineOfCodeArray -> {
            String joinedString = String.join(" ", lineOfCodeArray);
            try {
                logoWriter.write(joinedString + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        logoWriter.close();
        logoFile.createNewFile();
    }

    void printErrorsFile(String fileName, ArrayList<String> errorLogs, ArrayList<String[]> tokensArrayLexicallyAnalyzed) throws IOException {
        final String errorFileName = fileName.substring(0, fileName.lastIndexOf('.')) + "-HUGO-errores.txt";
        final File errorFile = new File("./", errorFileName);
        BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile));

        if (tokensArrayLexicallyAnalyzed.size() == 0) errorWriter.write(errorLogs.get(0));

        for (int index = 0; index < tokensArrayLexicallyAnalyzed.size(); index++) {
            String joinedString = String.join(" ", tokensArrayLexicallyAnalyzed.get(index));
            try {
                String codeLine = new String(getCounterLine(++count) + " " + joinedString + System.lineSeparator());
                String errorLine = errorLogs.get(index);
                if (errorLine != null) {
                    codeLine += "\t"+errorLogs.get(index);
                    codeLine += System.lineSeparator();
                }
                errorWriter.write(codeLine);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        errorWriter.close();
        errorFile.createNewFile();
        
    }

}