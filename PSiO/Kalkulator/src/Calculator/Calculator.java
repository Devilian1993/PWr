package Calculator;

import Calculator.NumberBaseUtils.*;

import java.lang.reflect.Array;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;

public class Calculator {
    private Stack<String> stack;
    private final static ArrayList<String> OPERATORS_LIST = Utils.getOperators();
    private final static ArrayList<String> FUNCTIONS_LIST = Utils.getFunctions();
    private final static HashMap<String, Integer> PRECEDENCE_MAP = Utils.getPrecedenceMap();
    private static ArrayList<String[]> calculationsHistory = new ArrayList<>();

    public Calculator() {
        stack = new Stack<>();
    }

    public static ArrayList<String[]> getCalculationsHistory() {
        return calculationsHistory;
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

    public String calculate(String equationString) {
        ArrayList<String> equation = infixToPostfix(equationString);
        for (String item : equation) {
            if (Utils.isNumeric(item)) {
                stack.push(item);
            } else if (OPERATORS_LIST.contains(item)) {
                try {
                    stack.push(Operations.operation(item, stack.pop(), stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            } else {
                try {
                    stack.push(Functions.function(item, stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            }
        }

        String result = Utils.reformatForErrors(Utils.reformatForConstants(stack.pop()));

        if (!result.equals("ERROR")) {
            calculationsHistory.add(new String[]{"DEC", "DEC", equationString + "=" + result});
        }

        return result;
    }

    public String calculate(String equationString, boolean asFunction) {
        ArrayList<String> equation = infixToPostfix(equationString);
        for (String item : equation) {
            if (Utils.isNumeric(item)) {
                stack.push(item);
            } else if (OPERATORS_LIST.contains(item)) {
                try {
                    stack.push(Operations.operation(item, stack.pop(), stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            } else {
                try {
                    stack.push(Functions.function(item, stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            }
        }

        String result = Utils.reformatForErrors(Utils.reformatForConstants(stack.pop()));

        if (!result.equals("ERROR") && !asFunction) {
            calculationsHistory.add(new String[]{"DEC", "DEC", equationString + "=" + result});
        }

        return result;
    }

    public String calculateBinary(String equationString, int inputBase, int resultBase) {
        ArrayList<String> equation = infixToPostfix(equationString);

        if (inputBase == 2) {
            for (int i = 0; i < equation.size(); i++) {
                if (Utils.isNumeric(equation.get(i))) {
                    equation.set(i, BinaryToDecimalConverter.convertToDecimal(equation.get(i)));
                }
            }
        }

        for (String item : equation) {
            if (Utils.isNumeric(item)) {
                stack.push(item);
            } else if (OPERATORS_LIST.contains(item)) {
                try {
                    stack.push(Operations.operation(item, stack.pop(), stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            } else {
                try {
                    stack.push(Functions.function(item, stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            }
        }

        String result = Utils.reformatForErrors(Utils.reformatForConstants(stack.pop()));

        if (result.equals("ERROR")) {
            return "ERROR";
        }

        result = resultBase == 10 ? result : DecimalToBinaryConverter.convertToBinary(result);

        if (!result.equals("ERROR")) {
            String inputBaseString = inputBase == 2 ? "BIN" : "DEC";
            String outputBaseString = resultBase == 2 ? "BIN" : "DEC";
            calculationsHistory.add(new String[]{inputBaseString, outputBaseString, equationString + "=" + result});
        }

        return result;
    }

    public String calculateHexadecimal(String equationString, int inputBase, int resultBase) {
        ArrayList<String> equation = infixToPostfix(equationString);

        if (inputBase == 16) {
            for (int i = 0; i < equation.size(); i++) {
                if (Utils.isNumeric(equation.get(i))) {
                    equation.set(i, HexToDecimalConverter.convertToDecimal(equation.get(i)));
                }
            }
        }

        for (String item : equation) {
            if (Utils.isNumeric(item)) {
                stack.push(item);
            } else if (OPERATORS_LIST.contains(item)) {
                try {
                    stack.push(Operations.operation(item, stack.pop(), stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            } else {
                try {
                    stack.push(Functions.function(item, stack.pop()));
                } catch (IllegalArgumentException e) {
                    return "ERROR";
                }
            }
        }

        String result = Utils.reformatForErrors(Utils.reformatForConstants(stack.pop()));

        if (result.equals("ERROR")) {
            return "ERROR";
        }

        result = resultBase == 10 ? result : DecimalToHexConverter.convertToHex(result);

        if (!result.equals("ERROR")) {
            String inputBaseString = inputBase == 16 ? "HEX" : "DEC";
            String outputBaseString = resultBase == 16 ? "HEX" : "DEC";
            calculationsHistory.add(new String[]{inputBaseString, outputBaseString, equationString + "=" + result});
        }

        return result;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.calculate("0.1 + 0.2"));
    }
}