package Calculator.NumberBaseUtils;

import java.math.BigDecimal;

public class BinaryToDecimalConverter {

    public static String convertToDecimal(String binaryStr) {
        try {
            boolean isNegative = binaryStr.startsWith("-");
            if (isNegative) {
                binaryStr = binaryStr.substring(1); // Usuń znak '-'
            }

            String[] parts = binaryStr.split("\\.");
            String integerPart = parts[0];
            String fractionalPart = parts.length > 1 ? parts[1] : "";

            BigDecimal decimalResult = new BigDecimal(Integer.parseInt(integerPart, 2));

            if (!fractionalPart.isEmpty()) {
                decimalResult = decimalResult.add(convertBinaryFractionToDecimal(fractionalPart));
            }

            return isNegative ? decimalResult.negate().toString() : decimalResult.toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid binary input: " + binaryStr);
        }
    }

    private static BigDecimal convertBinaryFractionToDecimal(String fractionalPart) {
        BigDecimal decimalFraction = BigDecimal.ZERO;
        BigDecimal divisor = BigDecimal.valueOf(2);

        for (int i = 0; i < fractionalPart.length(); i++) {
            if (fractionalPart.charAt(i) == '1') {
                decimalFraction = decimalFraction.add(BigDecimal.ONE.divide(divisor, 10, BigDecimal.ROUND_HALF_UP));
            }
            divisor = divisor.multiply(BigDecimal.valueOf(2));
        }

        return decimalFraction;
    }

    public static void main(String[] args) {
        System.out.println(convertToDecimal("1010.011")); // 10.375
        System.out.println(convertToDecimal("11.0010"));  // 3.125
        System.out.println(convertToDecimal("0.0001"));   // 0.0625
        System.out.println(convertToDecimal("-100.01"));  // -4.25
    }
}
