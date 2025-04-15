import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;
import pivot.*;

public class QuickSortLinkedListOptimised<T> extends AbstractSwappingSortingAlgorithm <T>{

    PivotSelector<T> pivotSelector;
    public QuickSortLinkedListOptimised(Comparator<? super T> comparator, PivotSelector<T> pivotSelector) {
        super(comparator);
        this.pivotSelector = pivotSelector;
    }

    private void partition()

    private void quicksort(List<T> list, int leftIndex, int rightIndex) {

    }

    @Override
    public List<T> sort(List<T> list) {
        return List.of();
    }
}
