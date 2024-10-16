import java.util.Scanner;

public class NWD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        while (a != b){
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        System.out.println(a);
    }
}
