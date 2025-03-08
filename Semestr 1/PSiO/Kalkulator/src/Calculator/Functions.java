package Calculator;

public class Functions {
    public static String sine(double x) {
        return Double.toString(Math.sin(x));
    }

    public static String cosine(double x) {
        return Double.toString(Math.cos(x));
    }

    public static String tan(double x) {
        return Double.toString(Math.tan(x));
    }

    public static String arcsine(double x) {
        return Double.toString(Math.asin(x));
    }

    public static String arccos(double x) {
        return Double.toString(Math.acos(x));
    }

    public static String arctan(double x) {
        return Double.toString(Math.atan(x));
    }

    public static String exponentiation(double x) {
        return Double.toString(Math.exp(x));
    }

    public static String naturalLog(double x) {
        return Double.toString(Math.log(x));
    }

    public static String decimalLog(double x) {
        return Double.toString(Math.log10(x));
    }

    public static String sqrt(double a) {
        return Double.toString(Math.sqrt(a));
    }

    public static String cbrt(double a) {
        return Double.toString(Math.cbrt(a));
    }

    public static String floor(double a) {
        return Double.toString(Math.floor(a));
    }

    public static String ceil(double a) {
        return Double.toString(Math.ceil(a));
    }

    public static String abs(double a) {
        return Double.toString(Math.abs(a));
    }

    public static String function(String function, String x) {
        double xNum = Utils.stringToDouble(x);
        if (function.equals("sin")) {
            return sine(xNum);
        } else if (function.equals("cos")) {
            return cosine(xNum);
        } else if (function.equals("tan")) {
            return tan(xNum);
        } else if (function.equals("asin")) {
            return arcsine(xNum);
        } else if (function.equals("acos")) {
            return arccos(xNum);
        } else if (function.equals("atan")) {
            return arctan(xNum);
        } else if (function.equals("exp")) {
            return exponentiation(xNum);
        } else if (function.equals("ln")) {
            return naturalLog(xNum);
        } else if (function.equals("log")) {
            return decimalLog(xNum);
        } else if (function.equals("abs")) {
            return abs(xNum);
        } else if (function.equals("sqrt")) {
            return sqrt(xNum);
        } else if (function.equals("cbrt")) {
            return cbrt(xNum);
        } else if (function.equals("floor")) {
            return floor(xNum);
        } else {
            return ceil(xNum);
        }
    }
}
