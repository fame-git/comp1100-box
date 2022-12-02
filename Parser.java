import java.util.Scanner;

/**
 * Note: You will need to have completed task 1 to complete this task.
 *
 * Welcome to task 2. In this task your job is to implement a simple parser.
 * It should be able to parser the following grammar rule:
 * <exp>    ::=  <term> | <term> + <exp> | <term> - <exp>
 * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 * <factor> ::=  <coefficient> | <coefficient> !
 * <coefficient> ::= <unsigned integer> | ( <exp> )
 *
 * Here are some rules you must abide by for this task:
 * 1. You may NOT modify any other classes in this task 2 package.
 * 2. You may create additional fields or methods to finish you implementation within this class.
 * <p>
 * Parsing, within the context of this lab, is the process of taking a bunch of tokens and
 * evaluating them. You will not need to 'evaluate' them within this class, instead, just
 * return an expression which can be evaluated.
 */
public class Parser {
    /**
     * The following exception should be thrown if the parse is faced with series of tokens that do not
     * correlate with any possible production rule.
     */
    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }

    // The tokenizer (class field) this parser will use.
    Tokenizer tokenizer;

    /**
     * Parser class constructor
     * Simply sets the tokenizer field.
     * **** please do not modify this part ****
     */
    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * To help you both test and understand what this parser is doing, we have included a main method
     * which you can run. Once running, provide a mathematical string to the terminal and you will
     * receive back the result of your parsing.
     */
    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         Example input you can try: ((1 + 2) * 5)/2
         Note that evaluations will round down to negative infinity (because they are integers).
         */
        System.out.println("Provide a mathematical string to be parsed:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new Tokenizer(input);

            // Print out the expression from the parser.
            Parser parser = new Parser(tokenizer);
            Exp expression = parser.parseExp();
            System.out.println("Parsing: " + expression.show());
            System.out.println("Evaluation: " + expression.evaluate());
        }
    }

    /**
     * Adheres to the grammar rule:
     * <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
     *
     * @return type: Exp.
     */
    public Exp parseExp() {
        /*
         TODO: Implement parse function for <exp>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint 1: you know that the first item will always be a term (according to the grammar).
         Hint 2: the possible grammar return '<term> + <exp>' correlates with the class (AddExp(term, exp)).
         */
        // ########## YOUR CODE STARTS HERE ##########

        String term = "";  // <term>
        Token expToken = null;  // '+' or '-'
        String exp = "";  //  <exp>
        boolean flag = false;  // False until the '+' or '-' found.
        int lbraCount = 0;  // To make pair of '(' and ')'.
        int intCount = 0;  // To mark if there are duplicate Int tokens.

        while (tokenizer.hasNext()) {
            Token current = tokenizer.current();
            if (!flag) {   // Not meet '+' or '-' yet, should add current token string to <term>.
                // An outer '(' found, pack everything behind it until the corresponding ')' found.
                if (current.getType() == Token.Type.LBRA) {
                    while (tokenizer.hasNext()) {
                        current = tokenizer.current();

                        // Duplicate Int token check inside the outer '()' pair.
                        if (current.getType() == Token.Type.INT) {
                            intCount += 1;
                        } else intCount = 0;
                        if (intCount >= 2) {
                            throw new IllegalProductionException("Duplicate INT token.");
                        }

                        if (current.getType() != Token.Type.RBRA) {  // Every time an inner ')' found ...
                            if (current.getType() == Token.Type.LBRA){
                                lbraCount += 1;
                            }
                            term += current.getToken();
                            tokenizer.next();
                        } else {  // Every time a ')' found, make pairs with previous '('s.
                            lbraCount -= 1;
                            term += current.getToken();
                            if (lbraCount == 0) { // Corresponding outer ')' found.
                                break;
                            }
                            tokenizer.next();
                        }
                    }
                } else if (current.getType() != Token.Type.ADD && current.getType() != Token.Type.SUB){  // Other token found, just add it to the <term>
                    term += current.getToken();
                } else {  // '+' or '-' found.
                    flag = true;
                    expToken = current;
                }
            } else {  // Flag is true, pack everything behind into <exp>
                exp += current.getToken();
            }

            // Duplicate Int token check.
            if (current.getType() == Token.Type.INT) {
                intCount += 1;
            } else intCount = 0;
            if (intCount >= 2) {
                throw new IllegalProductionException("Duplicate INT token.");
            }

            tokenizer.next();
        }
        if (term.equals("")) {  // null <term> (like ‘+3’)
            throw new IllegalProductionException("No <term> found in <exp>.");
        } else {
            Parser termParser = new Parser(new Tokenizer(term));
            if (exp.equals("")) {  // <exp> ::= <term>
                return termParser.parseTerm();
            }
            Parser expParser = new Parser(new Tokenizer(exp));
            if (expToken.getType() == Token.Type.ADD) {
                return new AddExp(termParser.parseTerm(), expParser.parseExp());  // <exp> ::= <term> + <exp>
            } else return new SubExp(termParser.parseTerm(), expParser.parseExp());  // <exp> ::= <term> - <exp>
        }

         // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
     *
     * @return type: Exp.
     */
    public Exp parseTerm() {
        /*
         TODO: Implement parse function for <term>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint: you know that the first item will always be a factor (according to the grammar).
         */
        // ########## YOUR CODE STARTS HERE ##########

        //  Duplicate Int Token has been checked in the parseExp, so no need here. Other logic is almost the same as parseExp.
        String factor = "";
        Token termToken = null;
        String term = "";
        boolean flag = false;
        int lbraCount = 0;
        while (tokenizer.hasNext()) {
            Token current = tokenizer.current();
            if (!flag) {
                if (current.getType() == Token.Type.LBRA) {
                    while (tokenizer.hasNext()) {
                        current = tokenizer.current();
                        if (current.getType() != Token.Type.RBRA) {
                            if (current.getType() == Token.Type.LBRA){
                                lbraCount += 1;
                            }
                            factor += current.getToken();
                            tokenizer.next();
                        } else {
                            lbraCount -= 1;
                            factor += current.getToken();
                            if (lbraCount == 0) {
                                break;
                            }
                            tokenizer.next();
                        }
                    }
                } else if (current.getType() != Token.Type.MUL && current.getType() != Token.Type.DIV){
                    factor += current.getToken();
                } else {
                    flag = true;
                    termToken = current;
                }
            } else {
                term += current.getToken();
            }
            tokenizer.next();
        }

        if (factor.equals("")) {
            throw new IllegalProductionException("No <factor> found in <term>.");
        } else {
            Parser factorParser = new Parser(new Tokenizer(factor));
            if (term.equals("")) {
                return factorParser.parseFactor();
            }
            Parser termParser = new Parser(new Tokenizer(term));
            if (termToken.getType() == Token.Type.MUL) {
                return new MultExp(factorParser.parseFactor(), termParser.parseTerm());
            } else return new DivExp(factorParser.parseFactor(), termParser.parseTerm());
        }
        // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <factor> ::=  <coefficient> | <coefficient> !
     *
     * @return type: Exp.
     */
    public Exp parseFactor() {
        /*
         TODO: Implement parse function for <factor>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint 1: you can use Integer.parseInt() to convert a string into an integer.
         Fun fact: Integer.parseInt() is using a parser!
         Hint 2: you know that the first item will always be a coefficient (according to the grammar).
         */
        // ########## YOUR CODE STARTS HERE ##########
        String coefficient = "";
        Token current = null;
        while(tokenizer.hasNext()) {
            current = tokenizer.current();
            coefficient += current.getToken();  // Just put EVERYTHING in the coefficient string.
            tokenizer.next();
        }
        if (current.getType() == Token.Type.FAC) {  // Check if the last token is '!'.
            coefficient = coefficient.substring(0, coefficient.length() - 1);  // Kick the last '!' out of coefficient string.
            if (coefficient.equals("")) {  // Throw exception if <factor> only contains a '!' with nothing before.
                throw new IllegalProductionException("No <coefficient> found in <factor>.");
            }
            Parser coefficientParser = new Parser(new Tokenizer(coefficient));
            return new FacExp(coefficientParser.parseCoefficient());  // <factor> ::= <coefficient>!
        } else {
            Parser coefficientParser = new Parser(new Tokenizer(coefficient));
            return coefficientParser.parseCoefficient();  // <factor> ::= <coefficient>
        }
        // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <coefficient> ::= <unsigned integer> | ( <exp> )
     *
     * @return type: Exp.
     */
    public Exp parseCoefficient() {
        /*
         TODO: Implement parse function for <coefficient>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         */
        // ########## YOUR CODE STARTS HERE ##########
        String exp = "";
        Token current = tokenizer.current();
        if (current.getType() == Token.Type.INT) {  // <coefficient> begin with Int token.
            tokenizer.next();
            if (!tokenizer.hasNext()) {  // <coefficient> contains only one token and is an Int token, which is correct.
                return new IntExp(Integer.parseInt(current.getToken()));  // <coefficient> ::= <unsigned integer>
            } else throw new IllegalProductionException("Wrong format.");  // <coefficient> begin with Int token but has other tokens followed, wrong format.
        } else if (current.getType() == Token.Type.LBRA) {  // <coefficient> begin with '(', simply pack everything as <exp> string.
            while (tokenizer.hasNext()) {
                current = tokenizer.current();
                exp += current.getToken();
                tokenizer.next();
            }
            if (current.getType() != Token.Type.RBRA) {  // The packed string doesn't end with ')', wrong format.
                throw new IllegalProductionException("Missing RBRA token.");
            } else {
                exp = exp.substring(1, exp.length() - 1);  // Take away the outer '()'.
                Parser expParser = new Parser(new Tokenizer(exp));
                return expParser.parseExp();  // <coefficient> ::= (<exp>)
            }
        } else throw new IllegalProductionException("Wrong format.");  // <coefficient> begin with neither Int nor '(', wrong format.

        // Change this return (if you want). It is simply a placeholder to prevent an error.
        // ########## YOUR CODE ENDS HERE ##########
    }
}
