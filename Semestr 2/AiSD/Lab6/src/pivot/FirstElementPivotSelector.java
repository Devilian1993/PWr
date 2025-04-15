package pivot;

import java.util.List;

public class FirstElementPivotSelector<E> implements PivotSelector<E> {
    @Override
    public int selectPivotIndex(List<E> list) {
        return 0;
    }
}
