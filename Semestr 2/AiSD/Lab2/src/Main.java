import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(i);
        }

        //SubswapIterator 1
        System.out.println("SubswapIterator");
        Iterator<Integer> iterator1 = list.iterator();

        SubswapIterator<Integer> subswapIterator1 = new SubswapIterator<>(iterator1, 4);

        while (subswapIterator1.hasNext()) {
            System.out.println(subswapIterator1.next());
        }

        System.out.println();

        //SubswapIterator 2
        Iterator<Integer> iterator2 = list.iterator();

        SubswapIterator<Integer> subswapIterator2 = new SubswapIterator<>(iterator2, 2);

        while (subswapIterator2.hasNext()) {
            System.out.println(subswapIterator2.next());
        }

        System.out.println();

        //SubswapIterator 3
        List<String> list2 = new ArrayList<>();
        list2.add("Ala");
        list2.add("Miała");
        list2.add("Kota");
        list2.add("Kot");
        list2.add("Miał");
        list2.add("Ale");

        Iterator<String> iterator3 = list2.iterator();

        SubswapIterator<String> subswapIterator3 = new SubswapIterator<>(iterator3, 3);

        while (subswapIterator3.hasNext()) {
            System.out.println(subswapIterator3.next());
        }

        System.out.println();

        //CollatzIterator
        System.out.println("CollatzIterator");
        int n = 100;
        CollatzIterator collatzIterator = new CollatzIterator(n);

        //for (Integer value : collatzIterator) {
        //    System.out.println(value);
        //}

        System.out.println();

        //RandomIterator
        String[] stringList = {"Zamienil", "Stryjek", "Siekierke", "Na", "Kijek"};
        RandomIterator<String> randomIteratorString = new RandomIterator<>(stringList);

        while (randomIteratorString.hasNext()) {
            System.out.println(randomIteratorString.next());
        }

        System.out.println();

        Integer[] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        RandomIterator<Integer> randomIteratorInt = new RandomIterator<>(intList);

        while (randomIteratorInt.hasNext()) {
            System.out.println(randomIteratorInt.next());
        }

        System.out.println();

        Integer[] intList1 = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
        RandomIterator<Integer> randomIteratorInt1 = new RandomIterator<>(intList1);

        while (randomIteratorInt1.hasNext()) {
            System.out.println(randomIteratorInt1.next());
        }

        System.out.println();

        String[] stringList1 = {null, null, null, null, "String", "Kolejny string"};
        RandomIterator<String> randomIteratorString1 = new RandomIterator<>(stringList1);

        while (randomIteratorString1.hasNext()) {
            System.out.println(randomIteratorString1.next());
        }

        System.out.println();

        String[] stringList2 = {};
        RandomIterator<String> randomIteratorString2 = new RandomIterator<>(stringList2);

        while (randomIteratorString2.hasNext()) {
            System.out.println(randomIteratorString2.next());
        }

        System.out.println();

        String[] stringList3 = null;
        RandomIterator<String> randomIteratorString3 = new RandomIterator<>(stringList3);

        while (randomIteratorString3.hasNext()) {
            System.out.println(randomIteratorString3.next());
        }
    }
}