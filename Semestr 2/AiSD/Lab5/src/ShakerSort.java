import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class ShakerSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public ShakerSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        int left = 0;
        int right = size - 1;
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = left; i <= right - 1; i++) {
                int nextIndex = i + 1;
                if (compare(list.get(i), list.get(nextIndex)) > 0) {
                    swap(list, i, nextIndex);
                    swapped = true;
                }
            }

            right--;

            for (int i = right; i >= left + 1; i--) {
                int previousIndex = i - 1;
                if (compare(list.get(i), list.get(previousIndex)) < 0) {
                    swap(list, i, previousIndex);
                    swapped = true;
                }
            }

            left++;
        }

        return list;
    }
}