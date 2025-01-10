public class Operations {
    public static String operation(String operationString, String a, String b) {
        double aNum = Utils.stringToDouble(a);
        double bNum = Utils.stringToDouble(b);
        if (operationString.equals("+")) {
            return Double.toString(bNum + aNum);
        } else if (operationString.equals("-")) {
            return Double.toString(bNum - aNum);
        } else if (operationString.equals("*")) {
            return Double.toString(bNum * aNum);
        } else if (operationString.equals("/")) {
            return Double.toString(bNum / aNum);
        } else {
            return Double.toString(Math.pow(bNum, aNum));
        }
    }
}
