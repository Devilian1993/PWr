import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

public class Calculator {
    Stack<String> stack;
    final static ArrayList<String> OPERATORS_LIST = Utils.getOperators();
    final static ArrayList<String> FUNCTIONS_LIST = Utils.getFunctions();
    final static HashMap<String, Integer> PRECEDENCE_MAP = Utils.getPrecedenceMap();

    public Calculator() {
        stack = new Stack<>();
    }

    // Shunting yard algorithm
    static ArrayList<String> infixToPostfix(String infix) {
        String[] infixArray = infix.split(" ");

        ArrayList<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        for (String item : infixArray) {
            if (Utils.isNumeric(item)) {
                output.add(item);
            } else if (FUNCTIONS_LIST.contains(item)) {
                operators.push(item);
            } else if (OPERATORS_LIST.contains(item)) {
                while (!operators.isEmpty() && !operators.peek().equals("(")
                        && ((PRECEDENCE_MAP.get(operators.peek()) > PRECEDENCE_MAP.get(item))
                        || ((PRECEDENCE_MAP.get(operators.peek()).equals(PRECEDENCE_MAP.get(item)) && !(operators.peek().equals("^")))))) {
                    output.add(operators.pop());
                }
                operators.push(item);
            } else if (item.equals("(")) {
                operators.push(item);
            } else if (item.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop();
                if (!operators.isEmpty() && FUNCTIONS_LIST.contains(operators.peek())) {
                    output.add(operators.pop());
                }
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    String calculate(String equationString) {
        ArrayList<String> equation = infixToPostfix(equationString);
        for (String item : equation) {
            if (Utils.isNumeric(item)) {
                stack.push(item);
            } else if (OPERATORS_LIST.contains(item)) {
                stack.push(Operations.operation(item, stack.pop(), stack.pop()));
            } else {
                stack.push(Functions.function(item, stack.pop()));
            }
        }

        return Utils.reformatForConstants(stack.pop());
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        double result = 0;

        for (int i = 1; i <= 200; i++) {
            result += Utils.stringToDouble(calculator.calculate(String.format("1 / %d ^ 2", i)));
        }

        System.out.println(calculator.calculate(String.format("sqrt  ( %f * 6 )", result)));
    }
}