
public class Token {
    enum Type {
        COMMAND,
        ARGS,
        EOF
    }
    String Name;
    Type type;

    public Token(String name, Type type) {
        this.Name = name;
        this.type = type;
    }

}
