import java.util.Scanner;

public class Is_prime {
    public static boolean is_prime(int n){
        if (n < 2) {
            return false;
        } else {
            for (int i = 2; i*i <= n; i++) {
                if (n%i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println(is_prime(n));
    }
}
