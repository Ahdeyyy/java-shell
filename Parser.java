public class Parser {
    // The string array is the input from the command line
    public Token[] parse (String src) {
        String[] tokens = src.split(" ");
        Token[] result = new Token[tokens.length];              
        for (int i = 0; i < tokens.length; i++) {
            if (i == 0) {
                result[i] = new Token(tokens[i], Token.Type.COMMAND);
            } else {
                result[i] = new Token(tokens[i], Token.Type.ARGS);
            }
        }

        return result;
    }
}
