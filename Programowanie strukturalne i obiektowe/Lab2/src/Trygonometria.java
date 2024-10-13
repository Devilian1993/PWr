public class Trygonometria {

    static double power(double x, int k) {
        double result = 1;
        for (int i = 1; i <= k; i++) {
            result *= x;
        }
        return result;
    }

    static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++){
            result *= i;
        }
        return result;
    }

    static double cosX(double x, int k){
        double result = 0;

        for (int i = 0; i <= k; i++) {
            result += power(-1, i)*(power(x, 2*i)/factorial(2*i));
        }
        return result;
    }

    static double sinX(double x, int k){
        double result = 0;

        for (int i = 0; i <= k; i++) {
            result += power(-1, i)*(power(x, 2*i + 1)/factorial(2*i + 1));
        }
        return result;
    }

    public static void main(String[] args) {
        double x = 3.141/6;
        int k = 12;
        System.out.println("Sin(x): " + sinX(x, k));
        System.out.println("Cos(x): " + cosX(x, k));
    }
}
