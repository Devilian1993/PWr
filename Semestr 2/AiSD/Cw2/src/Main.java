import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void zadanie1() {
        System.out.println("#####Zadanie 1#####");
        List<Integer> list1 = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        Iterator<Integer> iterator1 = list1.iterator();
        final int K = 3;
        Iterator<Integer> kthIterator = new KthElementIterator<>(iterator1, K);

        while(kthIterator.hasNext()) {
            System.out.println(kthIterator.next());
        }
    }

    public static void zadanie2() {
        System.out.println("#####Zadanie 2#####");
        final int N = 5;
        Iterator<Integer> generator = new NumberGeneratorIterator(5);

        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }
    }

    public static void zadanie3() {
        System.out.println("#####Zadanie 3#####");
        Iterator<Integer> generator = new FibonacciGenerator();

        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }
    }

    public static void zadanie4() {
        System.out.println("#####Zadanie 4#####");
        List<Integer> list1 = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        Iterator<Integer> iterator1 = list1.iterator();
        List<String> list2 = Arrays.asList(new String[]{"A", "B", "C", "D"});
        Iterator<String> iterator2 = list2.iterator();

        Iterator shuffleIterator = new ShuffleIterator(iterator1, iterator2);

        while (shuffleIterator.hasNext()) {
            System.out.println(shuffleIterator.next());
        }
    }

    public static void zadanie5() {
        System.out.println("#####Zadanie 5#####");
        int N = 100;
        Iterator<Integer> primeGenerator = new PrimeNumberGenerator(N);

        while (primeGenerator.hasNext()) {
            System.out.println(primeGenerator.next());
        }
    }
    public static void main(String[] args) {
            //zadanie1();
            //zadanie2();
            //zadanie3();
            //zadanie4();
            zadanie5();
        }
    }