import java.util.Scanner;
import java.nio.file.*;

class Main {
    public static void main(String[] args) {

        Path home = Paths.get("/foo/bar");

        System.out.println(home.getParent().toString());

        Scanner sc = new Scanner(System.in);

        Parser parser = new Parser();
        Shell shell = new Shell();

        while (shell.running) {
            System.out.print("$ ");
            String src = sc.nextLine();
            Token[] result = parser.parse(src);
            boolean err = shell.ExecuteCommand(result);
            if (err) {
                System.err.println("error executing command");
            }

        }

        // System.out.println("Directory: " + shell.directory);

        // for (Token token : result) {
        // System.out.println(token.Name + " " + token.type);
        // }

        sc.close();

    }
}