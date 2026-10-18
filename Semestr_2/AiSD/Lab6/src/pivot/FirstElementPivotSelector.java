package pivot;

import java.util.List;

public class FirstElementPivotSelector<E> implements PivotSelector<E> {
    @Override
    public E selectPivot(List<E> list, int left, int right) {
        return list.get(left);
    }
}
