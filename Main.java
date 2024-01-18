import java.util.Scanner;
// import java.nio.file.*;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Shell shell = new Shell();

        while (shell.running) {
            System.out.println("\n" + shell.directory.getFileName().toString());
            System.out.print("$ ");
            String src = sc.nextLine();
            Token[] result = parser.parse(src);
            boolean err = shell.ExecuteCommand(result);
            if (err) {
                System.err.println("error executing command");
            }

        }

        sc.close();

    }
}