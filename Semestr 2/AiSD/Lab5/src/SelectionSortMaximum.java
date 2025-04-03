import core.AbstractSwappingSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SelectionSortMaximum<T> extends AbstractSwappingSortingAlgorithm<T> {

    public SelectionSortMaximum(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        return List.of();
    }
}
