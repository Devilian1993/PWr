import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class InsertionSortBinarySearch<T> extends AbstractSwappingSortingAlgorithm<T> {

    public InsertionSortBinarySearch(Comparator<? super T> comparator) {
        super(comparator);
    }

    private int getSwapIndex(List<T> list, T value, int leftIndex) {
        int rightIndex = list.size();

        while (leftIndex < rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            if (compare(list.get(mid), value) < 0) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid;
            }
        }

        return leftIndex;
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        for (int i = size - 2; i >= 0; i--) {
            T value = list.get(i);
            int swapIndex = getSwapIndex(list, value, i + 1);

            for (int j = i; j < swapIndex - 1; j++) {
                swap(list, j, j + 1);
            }
        }
        return list;
    }
}
