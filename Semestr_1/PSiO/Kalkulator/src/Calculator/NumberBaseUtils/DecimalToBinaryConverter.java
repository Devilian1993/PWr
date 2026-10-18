package Calculator.NumberBaseUtils;

import java.math.BigDecimal;

public class DecimalToBinaryConverter {

    public static String convertToBinary(String decimalStr) {
        try {
            BigDecimal decimal = new BigDecimal(decimalStr);

            BigDecimal integerPart = new BigDecimal(decimal.toBigInteger());
            BigDecimal fractionalPart = decimal.subtract(integerPart);

            StringBuilder binaryResult = new StringBuilder(integerPart.toBigInteger().toString(2));

            if (fractionalPart.compareTo(BigDecimal.ZERO) > 0) {
                binaryResult.append(".");
                binaryResult.append(convertFractionToBinary(fractionalPart));
            }

            return binaryResult.toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid decimal input: " + decimalStr);
        }
    }

    private static String convertFractionToBinary(BigDecimal fractionalPart) {
        StringBuilder binaryFraction = new StringBuilder();
        BigDecimal two = BigDecimal.valueOf(2);
        int precision = 4;

        for (int i = 0; i < precision; i++) {
            fractionalPart = fractionalPart.multiply(two);
            if (fractionalPart.compareTo(BigDecimal.ONE) >= 0) {
                binaryFraction.append("1");
                fractionalPart = fractionalPart.subtract(BigDecimal.ONE);
            } else {
                binaryFraction.append("0");
            }

            if (fractionalPart.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }

        return binaryFraction.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToBinary("10.375")); // 1010.0110
        System.out.println(convertToBinary("3.14"));   // 11.0010
        System.out.println(convertToBinary("0.1"));    // 0.0001
        System.out.println(convertToBinary("-4.25"));  // -100.01
    }
}