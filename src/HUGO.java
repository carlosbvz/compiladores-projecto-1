import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HUGO {

    private static String fileName = "cuadrado.hugo";
    public static void main(String[] args) throws Exception {
        System.out.println("I am HUGO");
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        }
    }
}