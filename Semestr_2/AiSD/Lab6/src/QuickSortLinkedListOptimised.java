import core.AbstractSwappingSortingAlgorithm;

import java.util.*;

import pivot.*;

public class QuickSortLinkedListOptimised<T> extends AbstractSwappingSortingAlgorithm <T>{

    PivotSelector<T> pivotSelector;
    public QuickSortLinkedListOptimised(Comparator<? super T> comparator, PivotSelector<T> pivotSelector) {
        super(comparator);
        this.pivotSelector = pivotSelector;
    }

    public PivotSelector<T> getPivotSelector() {
        return pivotSelector;
    }

    private void mockSwap(List<T> list) {
        swap(list, 0, 0);
    }

    private void swapIteratorsValue(ListIterator<T> leftIterator, ListIterator<T> rightIterator, T leftValue, T rightValue, List<T> list) {
        leftIterator.set(rightValue);
        rightIterator.set(leftValue);
        mockSwap(list);
    }

    private int partition(List<T> list, int leftIndex, int rightIndex) {
        T pivotValue = pivotSelector.selectPivot(list ,leftIndex, rightIndex);

        ListIterator<T> leftIterator = list.listIterator(leftIndex);
        ListIterator<T> rightIterator = list.listIterator(rightIndex + 1);
        T leftValue = null;
        T rightValue = null;

        while(true) {
            do {
                if (!leftIterator.hasNext()) {
                    break;
                }
                leftValue = leftIterator.next();
            } while(compare(leftValue, pivotValue) < 0);

            do {
                if (!rightIterator.hasPrevious()) {
                    break;
                }
                rightValue = rightIterator.previous();
            } while (compare(rightValue, pivotValue) > 0);

            if (leftIterator.previousIndex() >= rightIterator.nextIndex()) {
                break;
            }
            swapIteratorsValue(leftIterator, rightIterator, leftValue, rightValue, list);
        }
        return rightIterator.nextIndex();
    }

    private void quicksort(List<T> list, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int partitionIndex = partition(list, leftIndex, rightIndex);
        quicksort(list, leftIndex, partitionIndex);
        quicksort(list, partitionIndex + 1, rightIndex);
    }

    @Override
    public List<T> sort(List<T> list) {
        quicksort(list, 0, list.size() - 1);
        return list;
    }
}
