package pivot;

import java.util.List;

public interface PivotSelector<E> {
    E selectPivot(List<E> list, int left, int right);
}
