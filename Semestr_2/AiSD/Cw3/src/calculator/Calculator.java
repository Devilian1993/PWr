package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private Stack<String> stack;
    private final static ArrayList<String> OPERATORS_LIST = Utils.getOperators();
    private final static ArrayList<String> FUNCTIONS_LIST = Utils.getFunctions();
    private final static HashMap<String, Integer> PRECEDENCE_MAP = Utils.getPrecedenceMap();

    public Calculator() {
        stack = new Stack<>();
    }


    // Shunting yard algorithm
    static ArrayList<String> infixToPostfix(String infix) {
        infix = Utils.reformatForCalculator(infix);
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

    public double calculate(String equationString) throws IllegalArgumentException {
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

        String result = Utils.reformat(stack.pop());

        stack.clear();
        return Utils.stringToDouble(result);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.calculate("0.1 + 0.2"));
    }
}