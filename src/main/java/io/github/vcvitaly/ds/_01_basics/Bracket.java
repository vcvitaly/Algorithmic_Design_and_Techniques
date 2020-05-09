package io.github.vcvitaly.ds._01_basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class CheckBrackets {

    private static final String SUCCESS = "Success";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            System.out.println(checkBrackets(text));
        }
    }

    static String checkBrackets(String text) {

        Stack<Bracket> openingBracketsStack = new Stack<>();
        for (int position = 1; position <= text.length(); ++position) {
            char next = text.charAt(position-1);

            if (isOpeningBracket(next)) {
                openingBracketsStack.push(new Bracket(next, position));
            }

            if (isClosingBracket(next)) {
                if (openingBracketsStack.isEmpty()) {
                    return String.valueOf(position);
                }

                Bracket bracket = openingBracketsStack.pop();
                if (!bracket.match(next)) {
                    return String.valueOf(position);
                }
            }
        }

        if (openingBracketsStack.isEmpty()) {
            return SUCCESS;
        } else {
            Bracket firstBracket = null;
            while (!openingBracketsStack.isEmpty()) {
                firstBracket = openingBracketsStack.pop();
            }
            return String.valueOf(firstBracket.position);
        }
    }

    private static boolean isClosingBracket(char next) {
        return next == ')' || next == ']' || next == '}';
    }

    private static boolean isOpeningBracket(char next) {
        return next == '(' || next == '[' || next == '{';
    }
}
