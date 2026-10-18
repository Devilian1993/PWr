import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.shuffle;

public class SelectionSort<T> {
    private Comparator<T> comparator;
    public SelectionSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public List<T> sort(List<T> list) {
        int swapIndex = list.size() - 1;

        for (int i = swapIndex; i >= 0; i--) {
            int minIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if(comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(list, i, minIndex);
                Utils.printList((List<Integer>) list);
            }
        }

        return list;
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
        SelectionSort<Integer> sort = new SelectionSort<>(comp);
        sort.sort(list);
        Utils.printList(list);
    }
}
