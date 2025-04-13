import core.AbstractSortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class MergeSortTriplePartition<T> extends AbstractSortingAlgorithm<T> {

    public MergeSortTriplePartition(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        return List.of();
    }
}
