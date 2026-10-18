import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShuffleIterator<E> implements Iterator<E> {
    private final Iterator<E> iterator1;
    private final Iterator<E> iterator2;
    private int counter;

    public ShuffleIterator(Iterator<E> iterator1, Iterator<E> iterator2) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.counter = 0;
    }

    @Override
    public boolean hasNext() {
        return iterator1.hasNext() || iterator2.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            if (counter % 2 == 0) {
                counter++;
                if (iterator1.hasNext()) {
                    return iterator1.next();
                } else {
                    return iterator2.next();
                }
            } else {
                counter++;
                if (iterator2.hasNext()) {
                    return iterator2.next();
                } else {
                    return iterator1.next();
                }

            }
        }
    }
}
