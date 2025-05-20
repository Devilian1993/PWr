import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

public class BogoSort<T> {
    Comparator<T> comparator;

    public BogoSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sort(List<T> list) {
        while (!list.stream().sorted(comparator).toList().equals(list)) {
            shuffle(list);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int length = 12;

        for (int i = 0; i < length; i++) {
            list.add(i);
        }

        shuffle(list);
        Comparator<Integer> comp = Comparator.naturalOrder();
        BogoSort<Integer> sort = new BogoSort<>(comp);
        sort.sort(list);
        Utils.printList(list);
    }
}
