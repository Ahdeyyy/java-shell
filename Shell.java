import java.io.File;
// import java.nio.file.*;

public class Shell {
    String directory;
    String[] history;
    int historyIndex;
    boolean running;

    // Map<String, String> env;
    public Shell() {
        this.directory = System.getProperty("user.dir");
        // this.directory = "";
        this.history = new String[100];
        this.historyIndex = 0;
        this.running = true;
        // this.env = new HashMap<String, String>();
    }

    public boolean ExecuteCommand(Token[] command) {
        switch (command[0].Name) {
            case "ls":
                this.ListDirectory();
                break;
            case "mkdir":
                this.MakeDirectory(command[1].Name);
                break;
            case "rmdir":
                this.RemoveDirectory(command[1].Name);
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

    private void RemoveDirectory(String dirName) {
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

    private void ListDirectory() {
        File currentDirectory = new File(this.directory);
        String[] files = currentDirectory.list();
        for (String file : files) {
            System.out.println(file);
        }
    }

}