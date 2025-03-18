import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        int n = 100;
        CollartzIterator collartzIterator = new CollartzIterator(n);

        for (Integer value : collartzIterator) {
            System.out.println(value);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(i);
        }

        Iterator iterator2 = list.iterator();

        SubswapIterator subswapIterator = new SubswapIterator(iterator2, 4);

        while (subswapIterator.hasNext()) {
            System.out.println(subswapIterator.next());
        }
    }
}