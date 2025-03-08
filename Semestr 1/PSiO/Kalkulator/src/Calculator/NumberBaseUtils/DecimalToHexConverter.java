package Calculator.NumberBaseUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalToHexConverter {

    public static String convertToHex(String decimalStr) {
        try {
            // Parsujemy liczbę jako BigDecimal dla precyzyjnych obliczeń
            BigDecimal decimal = new BigDecimal(decimalStr);

            // Sprawdzamy, czy liczba jest ujemna
            boolean isNegative = decimal.signum() < 0;
            if (isNegative) {
                decimal = decimal.abs();
            }

            // Wyodrębniamy część całkowitą i ułamkową
            BigDecimal integerPart = new BigDecimal(decimal.toBigInteger());
            BigDecimal fractionalPart = decimal.subtract(integerPart);

            // Konwersja części całkowitej na szesnastkową
            StringBuilder hexResult = new StringBuilder(integerPart.toBigInteger().toString(16).toUpperCase());

            // Konwersja części ułamkowej na szesnastkową
            if (fractionalPart.compareTo(BigDecimal.ZERO) > 0) {
                hexResult.append(".");
                hexResult.append(convertFractionToHex(fractionalPart));
            }

            // Dodanie znaku ujemnego, jeśli liczba była ujemna
            return isNegative ? "-" + hexResult.toString() : hexResult.toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid decimal input: " + decimalStr);
        }
    }

    private static String convertFractionToHex(BigDecimal fractionalPart) {
        StringBuilder hexFraction = new StringBuilder();
        BigDecimal multiplier = BigDecimal.valueOf(16);
        int precision = 4; // Maksymalnie 4 miejsca po przecinku

        for (int i = 0; i < precision; i++) {
            fractionalPart = fractionalPart.multiply(multiplier);
            int integerPart = fractionalPart.intValue();
            hexFraction.append(Integer.toHexString(integerPart).toUpperCase());
            fractionalPart = fractionalPart.subtract(BigDecimal.valueOf(integerPart));

            // Jeśli ułamek osiągnie dokładność 0, przerywamy
            if (fractionalPart.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }

        return hexFraction.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToHex("10.375")); // A.6
        System.out.println(convertToHex("255.9921875")); // FF.FF
        System.out.println(convertToHex("0.1")); // 0.1999
        System.out.println(convertToHex("-4.25")); // -4.4
    }
}