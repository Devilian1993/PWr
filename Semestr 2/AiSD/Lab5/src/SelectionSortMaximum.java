import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SelectionSortMaximum<T> extends AbstractSwappingSortingAlgorithm<T> {

    public SelectionSortMaximum(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int swapIndex = list.size() - 1;

        for (int i = swapIndex; i >= 0; i--) {
            int maxIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if(compare(list.get(j), list.get(maxIndex)) > 0) {
                    maxIndex = j;
                }
            }
            if (i != maxIndex) {
                swap(list, i, maxIndex);
            }
        }

        return list;
    }
}
