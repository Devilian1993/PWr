import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.shuffle;

public class ShakerSort<T> {
    private Comparator<T> comparator;
    public ShakerSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public void sort(List<T> list) {
        int size = list.size();

        int left = 0;
        int right = size - 1;
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = left; i <= right - 1; i++) {
                int nextIndex = i + 1;
                if (comparator.compare(list.get(i), list.get(nextIndex)) > 0) {
                    swap(list, i, nextIndex);
                    swapped = true;
                }
            }

            right--;

            for (int i = right; i >= left + 1; i--) {
                int previousIndex = i - 1;
                if (comparator.compare(list.get(i), list.get(previousIndex)) < 0) {
                    swap(list, i, previousIndex);
                    swapped = true;
                }
            }

            left++;
        }
    }

    public static void main(String[] args) {
        //List<Integer> list = new ArrayList<>();
        List<Integer> list = new ArrayList<>(){{
            add(76);
            add(71);
            add(5);
            add(57);
            add(12);
            add(50);
            add(20);
            add(93);
            add(20);
            add(55);
            add(92);
            add(3);
        }};
        //int length = 10;
//
        //for (int i = 0; i < length; i++) {
        //    list.add(i);
        //}

        shuffle(list);
        Comparator<Integer> comp = Comparator.naturalOrder();
        ShakerSort<Integer> sort = new ShakerSort<>(comp);
        sort.sort(list);
        Utils.printList(list);
    }
}
