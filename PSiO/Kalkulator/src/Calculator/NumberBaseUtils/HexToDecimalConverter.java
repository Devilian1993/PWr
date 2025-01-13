package Calculator.NumberBaseUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class HexToDecimalConverter {

    public static String convertToDecimal(String hexStr) {
        try {
            // Sprawdzanie, czy liczba jest ujemna
            boolean isNegative = hexStr.startsWith("-");
            if (isNegative) {
                hexStr = hexStr.substring(1); // Usuń znak '-'
            }

            // Podział na część całkowitą i ułamkową
            String[] parts = hexStr.split("\\.");
            String integerPart = parts[0];
            String fractionalPart = parts.length > 1 ? parts[1] : "";

            // Konwersja części całkowitej na dziesiętną
            BigDecimal decimalResult = new BigDecimal(new BigInteger(integerPart, 16));

            // Konwersja części ułamkowej na dziesiętną
            if (!fractionalPart.isEmpty()) {
                decimalResult = decimalResult.add(convertHexFractionToDecimal(fractionalPart));
            }

            // Dodanie znaku ujemnego, jeśli liczba była ujemna
            return isNegative ? "-" + decimalResult.toString() : decimalResult.toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hexadecimal input: " + hexStr);
        }
    }

    private static BigDecimal convertHexFractionToDecimal(String fractionalPart) {
        BigDecimal decimalFraction = BigDecimal.ZERO;
        BigDecimal divisor = BigDecimal.valueOf(16);

        for (int i = 0; i < fractionalPart.length(); i++) {
            char hexChar = fractionalPart.charAt(i);
            int value = Character.digit(hexChar, 16);
            if (value == -1) {
                throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexChar);
            }
            decimalFraction = decimalFraction.add(BigDecimal.valueOf(value).divide(divisor, 10, BigDecimal.ROUND_HALF_UP));
            divisor = divisor.multiply(BigDecimal.valueOf(16));
        }

        return decimalFraction;
    }

    public static void main(String[] args) {
        System.out.println(convertToDecimal("A.6")); // 10.375
        System.out.println(convertToDecimal("FF.FF")); // 255.9921875
        System.out.println(convertToDecimal("0.1999")); // 0.1
        System.out.println(convertToDecimal("-4.4")); // -4.25
    }
}
