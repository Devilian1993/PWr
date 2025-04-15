package pivot;

import java.util.List;
import java.util.Random;

public class RandomPivotSelector<E> implements PivotSelector<E> {
    @Override
    public E selectPivot(List<E> list, int left, int right) {
        Random rand = new Random();
        int index = rand.nextInt(right - left + 1) + left;
        return list.get(index);
    }
}
