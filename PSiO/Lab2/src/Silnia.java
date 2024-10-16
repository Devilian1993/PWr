public class Silnia {
    public static void main(String[] args) {
        int n = 6;
        int result = 1;
        for (int i = 2; i <= n; i++){
            result *= i;
        }
        System.out.println(result);
    }
}
