import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Potegowanie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if (b == 0){
            System.out.println(1);
        }
        else {
            int wynik = a;
            for (int i = 1; i < b; i++){
                wynik *= a;
            }
            System.out.println(wynik);
        }
    }
}