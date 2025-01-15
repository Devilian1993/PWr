package Calculator;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Utils {
    static public String reformatForErrors(String str) {
        if (str.equals("Infinity") || str.equals("-Infinity") || str.equals("NaN") || str.equals("∞") || str.equals("-∞")) {
            return "ERROR";
        } else {
            return str;
        }
    }

    static public String reformatForConstants(String str) {
        final double precision = 0.01;
        if (Math.abs(stringToDouble(str) - Math.PI) < precision) {
            return "pi";
        } else if (Math.abs(stringToDouble(str) - Math.E) < precision) {
            return "e";
        } else if (Utils.stringToDouble(str) == (int) Utils.stringToDouble(str)) {
            return Integer.toString((int) Utils.stringToDouble(str));
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(Utils.stringToDouble(str)).replaceAll(",", ".");
        }
    }

    static public String reformatForCalculator(String str) {
        str = str.replaceAll("([+*/%^()])", " $1 ");
        str = str.replaceAll("(?<![\\w)])(-?\\d*\\.?\\d+)", " $1 ");
        str = str.replaceAll("\\b(pi|e)\\b", " $1 ");

        str = str.replaceAll("(?<!\\w)(sin|cos|tan|asin|acos|atan|abs|exp|ln|log|sqrt|cbrt|floor|ceil)(?=\\()", " $1 ");

        return str.trim().replaceAll("\\s+", " ");
    }

    static public double stringToDouble(String str) {
        str = str.replace(",", ".");
        if (str.equals("pi")) {
            return Math.PI;
        } else if (str.equals("e")) {
            return Math.E;
        } else {
            return Double.parseDouble(str);
        }
    }

    static public boolean isNumeric(String str) {
        str = str.replace(",", ".");
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Pattern patternHex = Pattern.compile("[A-F]+");
        if (str.equals(null)) {
            return false;
        } else {
            return pattern.matcher(str).matches() || str.equals("pi") || str.equals("e") || patternHex.matcher(str).matches();
        }
    }

    static public HashMap<String, Integer> getPrecedenceMap() {
        HashMap<String, Integer> precedenceMap = new HashMap<>();

        precedenceMap.put("+", 1);
        precedenceMap.put("-", 1);
        precedenceMap.put("*", 2);
        precedenceMap.put("/", 2);
        precedenceMap.put("^", 3);

        return precedenceMap;
    }

    static public ArrayList<String> getOperators()
    {
        ArrayList<String> operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("^");

        return operators;
    }

    static public ArrayList<String> getFunctions()
    {

        ArrayList<String> functions = new ArrayList<>();
        functions.add("sin");
        functions.add("cos");
        functions.add("tan");
        functions.add("asin");
        functions.add("acos");
        functions.add("atan");
        functions.add("exp");
        functions.add("ln");
        functions.add("log");
        functions.add("sqrt");
        functions.add("cbrt");
        functions.add("floor");
        functions.add("ceil");
        functions.add("abs");

        return functions;
    }
}
