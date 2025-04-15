package pivot;

import java.util.List;
import java.util.Random;

public class RandomPivotSelector<E> implements PivotSelector<E> {
    @Override
    public E selectPivot(List<E> list) {
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        return rand.nextInt(list.size());
    }
}
