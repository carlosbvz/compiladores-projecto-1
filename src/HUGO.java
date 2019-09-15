import compiler.HugoCompiler;

public class HUGO {

    private static String fileName;
    private static HugoCompiler hugoCompiler = new HugoCompiler();
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Por favor provea el path del archivo de entrada");
            System.exit(0);
        } else {
            fileName = args[0];
            hugoCompiler.compile(fileName);

        }
    }
}