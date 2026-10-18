import java.util.Scanner;

public class Fibonacci {

    public static void n_fibonacci(int n) {
        if (n >= 1) {
            System.out.print("0 ");
        }
        if (n >= 2) {
            System.out.print("1 ");
        }
        if (n > 2) {
            int previous = 1;
            int previous_previous = 0;

            for (int i = 2; i < n; i++) {
                int current = previous + previous_previous;
                System.out.print(current + " ");
                previous_previous = previous;
                previous = current;
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        n_fibonacci(n);
    }
}