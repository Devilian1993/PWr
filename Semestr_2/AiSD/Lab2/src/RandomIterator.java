import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomIterator<E> implements Iterator<E> {

    Random rand;
    E[] list;
    boolean[] wasElementReturned;

    public RandomIterator(E[] list) {
        this.rand = new Random();
        this.list = list;
        if (list != null) {
            this.wasElementReturned = new boolean[list.length];
        }
    }

    private int getNextIndex() {
        return rand.nextInt(list.length);
    }

    @Override
    public boolean hasNext() {
        if (list == null) {
            return false;
        }

        for (int i = 0; i < list.length; i++) {
            if (!wasElementReturned[i]) {
                return true;
            }
        }

        return false;
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int index = getNextIndex();

        while (wasElementReturned[index]) {
            index = getNextIndex();
        }

        wasElementReturned[index] = true;

        return list[index];
    }
}
