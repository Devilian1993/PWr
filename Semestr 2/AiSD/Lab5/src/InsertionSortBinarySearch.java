import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class InsertionSortBinarySearch<T> extends AbstractSwappingSortingAlgorithm<T> {

    public InsertionSortBinarySearch(Comparator<? super T> comparator) {
        super(comparator);
    }

    private int getSwapIndex(List<T> list, T value, int rightIndex) {
        int leftIndex = 0;

        while (leftIndex < rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;

            if (compare(list.get(mid), value) <= 0) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid;
            }
        }

        if (leftIndex < list.size() && compare(list.get(leftIndex), value) <= 0) {
            return leftIndex + 1;
        } else {
            return leftIndex;
        }
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        int rightSortedIndex = 0;

        for (int i = 1; i < size; i++) {
            T value = list.get(i);
            int swapIndex = getSwapIndex(list, value, rightSortedIndex);

            for (int j = i; j > swapIndex; j--) {
                swap(list, j, j - 1);
            }

            rightSortedIndex++;
        }

        return list;
    }
}
