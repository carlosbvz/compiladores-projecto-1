package compiler;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HugoSyntaxAnalyzer {

    private static String[] hugoReservedWords = {"av", "avanza", "re","retrocede","gd","giraderecha","gi", "giraizquierda", "para", "fin"};
    private static String[] logoReservedWords = {"abiertos","abre","abreactualizar","abredialogo","abremidi","abrepuerto","ac","activa","activaventana","actualizaboton","actualizaestatico","adios","ajusta","alto","analiza","anterior","antes","aplica","arccos","arcodeelipse","arcsen","arctan","areaactiva","arreglo","ascii","atrapa","atras","av","avanza","ayuda","ayudadewindows","azar","a—adecadenalistbox","a—adelineacombobox","ba","bajalapiz","bajan","bajanariz","bal","balancea","balanceaizquierda","balanceo","barrera","bitinverso","bito","bitxor","bity","bl","bo","boarchivo","borra ","borrabarradesplazamiento","borraboton","borrabotonradio","borracadenalistbox","borracheckbox","borracombobox","borradialogo","borradir","borraestatico","borragroupbox","borralineacombobox","borralistbox","borrapaleta","borrapantalla","borrar ","borrararchivo","borratexto","borraventana","boton","bp","bt","cabecea","cabeceo","cai","cambiadirectorio","cambiasigno","car","caracter","carga","cargadib","cargadibtama—o","cargadll","cargagif","cd","centro","cerca","cierra","cierramidi","cierrapuerto","cl","co","coge","colorlapiz","colorpapel","colorrelleno","comodevuelve","contadoracero","contenido","continua","copiaarea","copiadef","cortaarea","creabarradesplazamiento ","creaboton","creabotonradio","creacheckbox","creacombobox","creadialogo","creadir","creadirectorio","creaestatico","creagroupbox","crealistbox","creaventana","cs","cuenta","cuentarepite","cursor","define","definemacro","definido","definidop","desplaza","desplazaizquierda","desplazax","desplazay","destapa","dev","devuelve","diferencia","directorio","directoriopadre","directorios","division","ed","edita","editafichero","ejecuta","ejecutaanaliza","elemento","empiezapoligono","encadena","entero","envia","enviavalorred","envolver","error","escribe","escribebotonradio","escribecadenapuerto","escribecaracterpuerto ","escribepuerto ","escribepuerto2","escribered","escribirarchivo","escritura","espera","estado","estadocheckbox","exclusivo","exp","finlec","finred","formatonumero ","fr","frase","gd","gi","giraderecha","giraizquierda ","goma","gotear","grosor","guarda","guardadialogo","guardadib","guardagif","habilitaboton","habilitacheckbox","habilitacombobox","hacia","haciaxyz","haz","hora","horamili","ig","igual","iguales","ila","im","improp","imts","imtsp","indiceimagen","iniciared","inversolapiz","iz","izquierda","lapiz","lc","lcs","lectura","leebarradesplazamiento","leebotonradio","leecadenapuerto","leecar","leecaracterpuerto","leecarc","leecarcs","leefoco","leelista","leepalabra","leepuerto","leepuerto2","leepuertojuegos","leered","leeseleccionlistbox ","leetecla","leetextocombobox","leevalorred","limpia","limpiapuerto","lista","listaarch","ll","llamadll","ln","local","log","lprop","lr","luz","lvars","macro","matriz","mayor","mayorque","mayusculas","mci","menor","menorque","menos","menosprimero","menosprimeros","mensaje","mensajemidi","miembro","minusculas","modobitmap","modopuerto","modotortuga","modoventana","modulo","mp","mpr","mps","mt","mu","muestra","muestrapoligono","muestrat","muestratortuga","no","nodos","noestado","noexclusivo","nogotear","nombre","nombres","nopas","nored","notraza","numero","o","ocultatortuga","ot","palabra","para","parada","paso","patronlapiz","pausa","pega","pegaenindice","perspectiva","pft","pintacolor","pixel","pla","poccr","ponareaactiva","ponbalanceo","ponbarradesplazamiento","poncabeceo","poncheckbox","poncl","ponclip","poncolorlapiz","poncolorpapel","poncolorrelleno","poncontador","poncp","poncursorespera","poncursornoespera","ponelemento","ponescritura","ponf","ponfoco","ponfondo","ponformatortuga","pong","pongrosor","ponindicebit","ponlapiz","ponlectura","ponlupa","ponluz","ponmargenes","ponmodobit","ponmodotortuga","ponmp","ponpatronlapiz","ponpixel","ponpos","ponposescritura","ponposlectura","ponprimero","ponprop","ponr","ponraton","ponred","ponronzal","ponrumbo","pontama—otipo","ponteclado","pontextocombobox","ponultimo","ponx","ponxy","ponxyz","pony","ponz","pos","pos3d","posicionate","poslectura","posraton","potencia","pp","ppr","preguntabox","pri","primero","primeros","primitiva","producto","prop","propiedad","prueba","ptt","pul","quitadibujotortuga","quitadll","quitaestado","quitared","quitarraton","quitateclado","radarccos","radarcsen","radarctan","radcos","radsen","radtan","raizcuadrada","rc","re","reazar","rectangulorrelleno","redondea","rellena","repite","resto","resultadoejecuta","retrocede","ro","ronzal","rotula","rumbo","seleccionbox","sen","shell","si","sic","sicierto","siempre","sievento","sif","sifalso","sinobox","sired","sisino","sistema","siverdadero","sl","standout","subelapiz","suenawave","suma","tama—odecorado","tama—odibujo","tama—ogif","tama—otipo","tan","tapa","tapado","tapanombre","tecla","terminapoligono","texto","tienebarra","tipo","tono","tortuga","tortugas","traza","ul","ultimo","unste","vacia","vacio","valor","var","ventanadepurador","vira","visible","y"};
    
    private String getNotSupportedTokenErrorMessage(String token) {
        return "Advertencia: instrucción \""+token+"\" no es soportada por esta versión.";
    }
    private String getUnKnownTokenErrorMessage(String token) {
        return "ERROR 100: comando desconocido \""+token+"\".";
    }
    private Boolean isTokenHugoWord(String token) {
        Boolean isTokenHugoWord = false;
        for (int reservedWordIndex = 0; reservedWordIndex < hugoReservedWords.length; reservedWordIndex++) {
            if (token.equals(hugoReservedWords[reservedWordIndex])) {
                isTokenHugoWord = true;
                break;
            }
        }
        return isTokenHugoWord;
    }
    private Boolean isTokenLogoWord(String token) {
        Boolean isTokenLogoWord = false;
        for (int reservedWordIndex = 0; reservedWordIndex < logoReservedWords.length; reservedWordIndex++) {
            if (token.equals(logoReservedWords[reservedWordIndex])) {
                isTokenLogoWord = true;
                break;
            }
        }
        return isTokenLogoWord;
    }

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

    private ArrayList<String> analyzeTokensInLineOfCode(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {
        
        ArrayList<String> basicErrors = new ArrayList<String>();

        // Check every line of code
        for (int lineIndex = 0; lineIndex < tokensArrayLexicallyAnalyzed.size(); lineIndex++) {

            String lineErrorMessage = null;
            Boolean shouldIgnoreRestOfLine = false;
            // Checks the first token per line, which should be a command
            String commandToken = tokensArrayLexicallyAnalyzed.get(lineIndex)[0];
            Boolean isHugoWord = isTokenHugoWord(commandToken);
            Boolean isLogoWord = isTokenLogoWord(commandToken);
            if (!isHugoWord && isLogoWord) { // Checks if commandToken is Logo reserved workd
                lineErrorMessage = getNotSupportedTokenErrorMessage(commandToken);
            } else if(!isHugoWord && !isLogoWord) { // Command token is a un-known word (no hugo, no logo)
                lineErrorMessage = getUnKnownTokenErrorMessage(commandToken);
                shouldIgnoreRestOfLine = true;
            }

            // Check the rest of tokens in line of code
            if (!shouldIgnoreRestOfLine) {
                for (int tokenIndex = 1; tokenIndex < tokensArrayLexicallyAnalyzed.get(lineIndex).length; tokenIndex++) {
                    String token = tokensArrayLexicallyAnalyzed.get(lineIndex)[tokenIndex];
                    // System.out.println("Token#: "+tokenIndex+" token value: "+token);
                }
            }

            if(lineErrorMessage != null) basicErrors.add(lineErrorMessage);
        }
        
        return basicErrors.size() > 0? basicErrors: null;
    }

    public ArrayList<String> analyze(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {

        ArrayList<String> errorsListArray = new ArrayList<String>();
        // Checks for basic general structure 'para/fin' tokens words present in code
        String generalStructureErrorMessage = analyzeTokensGeneralStructure(tokensArrayLexicallyAnalyzed);
        if (generalStructureErrorMessage != null) errorsListArray.add(generalStructureErrorMessage);

        // General Analysis, line by line
        ArrayList<String> basicErrors = analyzeTokensInLineOfCode(tokensArrayLexicallyAnalyzed);
        if(basicErrors != null) errorsListArray.addAll(basicErrors);

        return errorsListArray;

    }

}