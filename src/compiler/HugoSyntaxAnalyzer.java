package compiler;

import java.util.ArrayList;

import java.util.HashMap;

public class HugoSyntaxAnalyzer {

    private static String[] hugoReservedWords = { "av", "avanza", "re", "retrocede", "gd", "giraderecha", "gi",
            "giraizquierda", "para", "fin", "haz", "bp", "borrapantalla", "sl", "subelapiz", "bl", "bajalapiz",
            "goma", "centro", "lapiznormal", "ponlapiz", "ot", "ocultatortuga", "mt", "muestratortuga", "poncl",
            "poncolorlapiz", "poncolorrelleno", "rellena", "repite"};

            
    private static String[] logoReservedWords = { "abiertos", "abre", "abreactualizar", "abredialogo", "abremidi",
            "abrepuerto", "ac", "activa", "activaventana", "actualizaboton", "actualizaestatico", "adios", "ajusta",
            "alto", "analiza", "anterior", "antes", "aplica", "arccos", "arcodeelipse", "arcsen", "arctan",
            "areaactiva", "arreglo", "ascii", "atrapa", "atras", "av", "avanza", "ayuda", "ayudadewindows", "azar",
            "a—adecadenalistbox", "a—adelineacombobox", "ba", "bajalapiz", "bajan", "bajanariz", "bal", "balancea",
            "balanceaizquierda", "balanceo", "barrera", "bitinverso", "bito", "bitxor", "bity", "bl", "bo", "boarchivo",
            "borra ", "borrabarradesplazamiento", "borraboton", "borrabotonradio", "borracadenalistbox",
            "borracheckbox", "borracombobox", "borradialogo", "borradir", "borraestatico", "borragroupbox",
            "borralineacombobox", "borralistbox", "borrapaleta", "borrapantalla", "borrar ", "borrararchivo",
            "borratexto", "borraventana", "boton", "bp", "bt", "cabecea", "cabeceo", "cai", "cambiadirectorio",
            "cambiasigno", "car", "caracter", "carga", "cargadib", "cargadibtama—o", "cargadll", "cargagif", "cd",
            "centro", "cerca", "cierra", "cierramidi", "cierrapuerto", "cl", "co", "coge", "colorlapiz", "colorpapel",
            "colorrelleno", "comodevuelve", "contadoracero", "contenido", "continua", "copiaarea", "copiadef",
            "cortaarea", "creabarradesplazamiento ", "creaboton", "creabotonradio", "creacheckbox", "creacombobox",
            "creadialogo", "creadir", "creadirectorio", "creaestatico", "creagroupbox", "crealistbox", "creaventana",
            "cs", "cuenta", "cuentarepite", "cursor", "define", "definemacro", "definido", "definidop", "desplaza",
            "desplazaizquierda", "desplazax", "desplazay", "destapa", "dev", "devuelve", "diferencia", "directorio",
            "directoriopadre", "directorios", "division", "ed", "edita", "editafichero", "ejecuta", "ejecutaanaliza",
            "elemento", "empiezapoligono", "encadena", "entero", "envia", "enviavalorred", "envolver", "error",
            "escribe", "escribebotonradio", "escribecadenapuerto", "escribecaracterpuerto ", "escribepuerto ",
            "escribepuerto2", "escribered", "escribirarchivo", "escritura", "espera", "estado", "estadocheckbox",
            "exclusivo", "exp", "finlec", "finred", "formatonumero ", "fr", "frase", "gd", "gi", "giraderecha",
            "giraizquierda ", "goma", "gotear", "grosor", "guarda", "guardadialogo", "guardadib", "guardagif",
            "habilitaboton", "habilitacheckbox", "habilitacombobox", "hacia", "haciaxyz", "haz", "hora", "horamili",
            "ig", "igual", "iguales", "ila", "im", "improp", "imts", "imtsp", "indiceimagen", "iniciared",
            "inversolapiz", "iz", "izquierda", "lapiz", "lc", "lcs", "lectura", "leebarradesplazamiento",
            "leebotonradio", "leecadenapuerto", "leecar", "leecaracterpuerto", "leecarc", "leecarcs", "leefoco",
            "leelista", "leepalabra", "leepuerto", "leepuerto2", "leepuertojuegos", "leered", "leeseleccionlistbox ",
            "leetecla", "leetextocombobox", "leevalorred", "limpia", "limpiapuerto", "lista", "listaarch", "ll",
            "llamadll", "ln", "local", "log", "lprop", "lr", "luz", "lvars", "macro", "matriz", "mayor", "mayorque",
            "mayusculas", "mci", "menor", "menorque", "menos", "menosprimero", "menosprimeros", "mensaje",
            "mensajemidi", "miembro", "minusculas", "modobitmap", "modopuerto", "modotortuga", "modoventana", "modulo",
            "mp", "mpr", "mps", "mt", "mu", "muestra", "muestrapoligono", "muestrat", "muestratortuga", "no", "nodos",
            "noestado", "noexclusivo", "nogotear", "nombre", "nombres", "nopas", "nored", "notraza", "numero", "o",
            "ocultatortuga", "ot", "palabra", "para", "parada", "paso", "patronlapiz", "pausa", "pega", "pegaenindice",
            "perspectiva", "pft", "pintacolor", "pixel", "pla", "poccr", "ponareaactiva", "ponbalanceo",
            "ponbarradesplazamiento", "poncabeceo", "poncheckbox", "poncl", "ponclip", "poncolorlapiz", "poncolorpapel",
            "poncolorrelleno", "poncontador", "poncp", "poncursorespera", "poncursornoespera", "ponelemento",
            "ponescritura", "ponf", "ponfoco", "ponfondo", "ponformatortuga", "pong", "pongrosor", "ponindicebit",
            "ponlapiz", "ponlectura", "ponlupa", "ponluz", "ponmargenes", "ponmodobit", "ponmodotortuga", "ponmp",
            "ponpatronlapiz", "ponpixel", "ponpos", "ponposescritura", "ponposlectura", "ponprimero", "ponprop", "ponr",
            "ponraton", "ponred", "ponronzal", "ponrumbo", "pontama—otipo", "ponteclado", "pontextocombobox",
            "ponultimo", "ponx", "ponxy", "ponxyz", "pony", "ponz", "pos", "pos3d", "posicionate", "poslectura",
            "posraton", "potencia", "pp", "ppr", "preguntabox", "pri", "primero", "primeros", "primitiva", "producto",
            "prop", "propiedad", "prueba", "ptt", "pul", "quitadibujotortuga", "quitadll", "quitaestado", "quitared",
            "quitarraton", "quitateclado", "radarccos", "radarcsen", "radarctan", "radcos", "radsen", "radtan",
            "raizcuadrada", "rc", "re", "reazar", "rectangulorrelleno", "redondea", "rellena", "repite", "resto",
            "resultadoejecuta", "retrocede", "ro", "ronzal", "rotula", "rumbo", "seleccionbox", "sen", "shell", "si",
            "sic", "sicierto", "siempre", "sievento", "sif", "sifalso", "sinobox", "sired", "sisino", "sistema",
            "siverdadero", "sl", "standout", "subelapiz", "suenawave", "suma", "tama—odecorado", "tama—odibujo",
            "tama—ogif", "tama—otipo", "tan", "tapa", "tapado", "tapanombre", "tecla", "terminapoligono", "texto",
            "tienebarra", "tipo", "tono", "tortuga", "tortugas", "traza", "ul", "ultimo", "unste", "vacia", "vacio",
            "valor", "var", "ventanadepurador", "vira", "visible", "y" };
    private static HashMap<String, String> hugoSymbolHashMapTable = new HashMap<String, String>();
    private static HashMap<String, String> hugoVariablesHashMap = new HashMap<String, String>();

    void loadSymbolTable() {
        hugoSymbolHashMapTable.put("av", "Integer");
        hugoSymbolHashMapTable.put("avanza", "Integer");
        hugoSymbolHashMapTable.put("re", "Integer");
        hugoSymbolHashMapTable.put("retrocede", "Integer");
        hugoSymbolHashMapTable.put("gd", "Integer");
        hugoSymbolHashMapTable.put("giraderecha", "Integer");
        hugoSymbolHashMapTable.put("gi", "Integer");
        hugoSymbolHashMapTable.put("giraizquierda", "Integer");
        hugoSymbolHashMapTable.put("para", "String");
        hugoSymbolHashMapTable.put("fin", "Empty");
        hugoSymbolHashMapTable.put("bp", "Empty");
        hugoSymbolHashMapTable.put("borrapantalla", "Empty");
        hugoSymbolHashMapTable.put("sl", "Empty");
        hugoSymbolHashMapTable.put("subelapiz", "Empty");
        hugoSymbolHashMapTable.put("bl", "Empty");
        hugoSymbolHashMapTable.put("bajalapiz", "Empty");
        hugoSymbolHashMapTable.put("goma", "Empty");
        hugoSymbolHashMapTable.put("ot", "Empty");
        hugoSymbolHashMapTable.put("ocultatortuga", "Empty");
        hugoSymbolHashMapTable.put("mt", "Empty");
        hugoSymbolHashMapTable.put("muestratortuga", "Empty");
        hugoSymbolHashMapTable.put("lapiznormal", "Empty");
        hugoSymbolHashMapTable.put("ponlapiz", "Empty");
        hugoSymbolHashMapTable.put("poncl", "Integer");
        hugoSymbolHashMapTable.put("poncolorlapiz", "Integer");
        hugoSymbolHashMapTable.put("poncolorrelleno", "Integer");
        hugoSymbolHashMapTable.put("rellena", "Empty");
        hugoSymbolHashMapTable.put("centro", "Empty");
        hugoSymbolHashMapTable.put("repite", "Function");
    }
    private void saveVariable(String[] lineOfCodeArray) {
        String key = lineOfCodeArray[1].substring(1);
        String value = lineOfCodeArray[2];
        hugoVariablesHashMap.put(key, value);
    }
    // Error Messages
    private String getNotSupportedTokenErrorMessage(String token) {
        return "Advertencia: Instrucción \"" + token + "\" no es soportada por esta versión.";
    }

    private String getUnKnownTokenErrorMessage(String token) {
        return "ERROR 100: Comando desconocido \"" + token + "\".";
    }

    private String getInvalidTypeErrorMessage(String tokenArgument, String tokenCommand) {
        return "ERROR 200: Tipo de dato inválido \"" + tokenArgument + "\" para el comando \"" + tokenCommand + "\".";
    }

    private String getInvalidAmountArguments(Integer argumentsNumber, String tokenCommand) {
        return "ERROR 300: Cantidad inválida de argumentos (" + argumentsNumber + ") para el comando \"" + tokenCommand
                + "\".";
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

    private Boolean isTokenAnInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isSettingVariable(String commandToken) {
        return commandToken.equals("haz");
    }

    private Boolean isTokenVariable(String token) {
        return (token.charAt(0) == ':');
    }

    private Boolean isTokenDeclaringVariable(String token) {
        return (token.charAt(0) == '"');
    }

    private Boolean isTokenValidArgument(String token, String commandToken, Integer tokenIndex) {
        Boolean isValidTokenType = false;

        if (isSettingVariable(commandToken)) {
            if (tokenIndex == 1 && isTokenDeclaringVariable(token)) isValidTokenType = true;
            else if (tokenIndex == 2) {
                if (isTokenDeclaringVariable(token) || isTokenVariable(token)) isValidTokenType = true;
            }
        } else {
            String tokenType = hugoSymbolHashMapTable.get(commandToken);
            Boolean isTokenAnInteger = isTokenAnInteger(token);

            switch (tokenType) {
            case "String":
                if (!isTokenAnInteger)
                    isValidTokenType = true;
                break;
            case "Integer":
                if (isTokenAnInteger)
                    isValidTokenType = true;
                break;
            default:
                isValidTokenType = false;
            }
        }
        return isValidTokenType;
    }

    private String analyzeTokensGeneralStructure(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {

        String openWord = "para";
        String closingWord = "fin";
        Boolean hasOpenWord = false;
        Boolean hasClosingWord = false;

        for (int lineIndex = 0; lineIndex < tokensArrayLexicallyAnalyzed.size(); lineIndex++) {
            for (int tokenIndex = 0; tokenIndex < tokensArrayLexicallyAnalyzed.get(lineIndex).length; tokenIndex++) {
                String token = tokensArrayLexicallyAnalyzed.get(lineIndex)[tokenIndex];
                if (token.equals(openWord))
                    hasOpenWord = true;
                else if (token.equals(closingWord))
                    hasClosingWord = true;
            }
        }
        // Specific Analysis, General 'fin/para' structure
        if (!hasOpenWord && !hasClosingWord)
            return "ERROR 000: El código fuente no contiene palabras reservadas 'para' ni 'fin'.";
        else if (!hasOpenWord)
            return "ERROR 000: El código fuente no contiene palabra reservada 'para'.";
        else if (!hasClosingWord)
            return "ERROR 000: El código fuente no contiene palabra reservada 'fin'.";
        return null;
    }

    private ArrayList<String> analyzeTokensInLineOfCode(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {

        ArrayList<String> basicErrors = new ArrayList<String>();

        // Check every line of code
        for (int lineIndex = 0; lineIndex < tokensArrayLexicallyAnalyzed.size(); lineIndex++) {

            String lineErrorMessage = null;
            Boolean shouldIgnoreRestOfLine = false;
            String[] lineOfCodeArray = tokensArrayLexicallyAnalyzed.get(lineIndex);
            String commandToken = lineOfCodeArray[0];
            Boolean isHugoWord = isTokenHugoWord(commandToken);
            Boolean isLogoWord = isTokenLogoWord(commandToken);
            Integer argumentsPerCommand = lineOfCodeArray.length - 1;
            Boolean isSettingVariable = isSettingVariable(commandToken);

            if (!isHugoWord && isLogoWord) { // Checks if commandToken is Logo reserved workd
                lineErrorMessage = getNotSupportedTokenErrorMessage(commandToken);
                shouldIgnoreRestOfLine = true;
            } else if (!isHugoWord && !isLogoWord) { // Command token is a un-known word (no hugo, no logo)
                lineErrorMessage = getUnKnownTokenErrorMessage(commandToken);
                shouldIgnoreRestOfLine = true;
            } else if (isSettingVariable) {
                if (argumentsPerCommand != 2) {
                    lineErrorMessage += getInvalidAmountArguments(argumentsPerCommand, commandToken);
                }
                shouldIgnoreRestOfLine = true;
            }

            // Check the rest of tokens in line of code, which basically are the command's
            // arguments
            if (!shouldIgnoreRestOfLine) {

                if (commandToken.equals("repite")) {
                    // Here we handle 'repite' since it is a very ... complex operation
                    System.out.println("Repite");
                } else {
                    // Error if amount of arguments is not supported
                    if (argumentsPerCommand > 1) {
                        if (lineErrorMessage == null)
                            lineErrorMessage = "";
                        else
                            lineErrorMessage += "/n";
                        lineErrorMessage += getInvalidAmountArguments(argumentsPerCommand, commandToken);
                    } else {
                        for (int tokenIndex = 1; tokenIndex < tokensArrayLexicallyAnalyzed
                                .get(lineIndex).length; tokenIndex++) {
                            String token = lineOfCodeArray[tokenIndex];
                            Boolean isTokenValidArgument = isTokenValidArgument(token, commandToken, tokenIndex);

                            if (!isTokenValidArgument) {
                                if (lineErrorMessage == null)
                                    lineErrorMessage = "";
                                else
                                    lineErrorMessage += "/n";
                                lineErrorMessage += getInvalidTypeErrorMessage(token, commandToken);
                            }
                        }
                    }
                }

                
            }

            if (isSettingVariable && lineErrorMessage == null) {
                saveVariable(lineOfCodeArray);
            }
            
            basicErrors.add(lineErrorMessage);
        }

        return basicErrors.size() > 0 ? basicErrors : null;
    }

    public ArrayList<String> analyze(ArrayList<String[]> tokensArrayLexicallyAnalyzed) {

        loadSymbolTable();

        ArrayList<String> errorsListArray = new ArrayList<String>();
        // Checks for basic general structure 'para/fin' tokens words present in code
        String generalStructureErrorMessage = analyzeTokensGeneralStructure(tokensArrayLexicallyAnalyzed);

        if (generalStructureErrorMessage != null)
            errorsListArray.add(generalStructureErrorMessage);

        // General Analysis, line by line
        ArrayList<String> basicErrors = analyzeTokensInLineOfCode(tokensArrayLexicallyAnalyzed);
        if (basicErrors != null)
            errorsListArray.addAll(basicErrors);

        // System.out.println(Arrays.asList(errorsListArray));

        return errorsListArray;

    }

}