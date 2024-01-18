import java.io.File;
import java.nio.file.*;
import java.util.Scanner;

public class Shell {
    Path directory;
    String[] history;
    int historyIndex;
    boolean running;

    // Map<String, String> env;
    public Shell() {
        String shellUrl = System.getProperty("user.dir");
        this.directory = Paths.get(shellUrl);
        // this.directory = "";
        this.history = new String[100];
        this.historyIndex = 0;
        this.running = true;
        // this.env = new HashMap<String, String>();
    }

    public boolean ExecuteCommand(Token[] command) {
        // TODO: Handle errors
        switch (command[0].Name) {
            case "ls":
                if (command.length > 1) {
                    this.ListDirectory(command[1].Name);
                } else {
                    this.ListDirectory(null);
                }
                break;
            case "mkdir":
                this.MakeDirectory(command[1].Name);
                break;
            case "rm":
                if (command.length < 2) {
                    System.out.println("Specify the directory to be removed");
                }
                this.Remove(command[1].Name);
                break;
            case "cd":
                if (command.length < 2) {
                    System.out.println("Specify the directory to change to");
                }
                this.ChangeDirectory(command[1].Name);
                break;
            case "touch":
                if (command.length < 2) {
                    System.out.println("Specify the file to be created");
                }
                this.Touch(command[1].Name);
                break;
            case "pwd":
                System.out.println(this.directory);
                break;

            case "cat":
                if (command.length < 2) {
                    System.out.println("Specify the file to be read");
                }
                this.Cat(command[1].Name);
                break;

            case "quit":
                this.running = false;
                break;
            default:
                System.out.println("Invalid Command");
                return true;
        }
        return false;
    }

    private void Cat(String fileName) {
        String newFile = this.directory + "/" + fileName;
        File file = new File(newFile);
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Could not read file");
        }
    }

    private void Touch(String fileName) {
        String newFile = this.directory + "/" + fileName;
        File file = new File(newFile);
        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create file");
        }
    }

    private void ChangeDirectory(String dir) {
        if (dir == null) {
            return;
        }

        Path dirPath = Paths.get(dir);

        if (dirPath.isAbsolute()) {
            this.directory = dirPath;

        } else {
            this.directory = Paths.get(this.directory.toString() + "/" + dir);
        }

        this.directory = this.directory.normalize();

    }

    private void Remove(String dirName) {
        String newDir = this.directory + "/" + dirName;
        File directory = new File(newDir);
        boolean success = directory.delete();
        if (!success) {
            System.out.println("could not remove directory " + dirName);
        }

    }

    private void MakeDirectory(String dirName) {
        String newDir = this.directory + "/" + dirName;
        File currentDirectory = new File(newDir);
        if (currentDirectory.exists()) {
            System.out.println("directory already exists.");
            return;
        }
        currentDirectory.mkdir();
    }

    private void ListDirectory(String dir) {
        File currentDirectory;

        if (dir != null) {

            Path dirPath = Paths.get(dir);

            if (dirPath.isAbsolute()) {
                currentDirectory = new File(dir);
            } else {
                currentDirectory = new File(this.directory.toString() + "/" + dir);
            }
        } else {
            currentDirectory = this.directory.toFile();
        }

        if (!currentDirectory.exists()) {
            System.out.printf("Directory %s not found\n", dir);
            return;
        }

        String[] files = currentDirectory.list();

        if (files.length > 0) {
            for (String file : files) {
                System.out.println(file);
            }
        }
    }

}