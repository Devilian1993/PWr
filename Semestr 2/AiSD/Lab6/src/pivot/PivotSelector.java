package pivot;

import java.util.List;

public interface PivotSelector<E> {
    int selectPivotIndex(List<E> list);
}
