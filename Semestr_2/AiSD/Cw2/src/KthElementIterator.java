import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthElementIterator<E> implements Iterator<E> {
    Iterator<E> baseIterator;
    int k;
    int i;

    KthElementIterator(Iterator<E> baseIterator, int k) {
        this.baseIterator = baseIterator;
        this.k = k;
        this.i = 1;
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (i != k) {
            baseIterator.next();
            i++;
        }
        i = 1;
        return baseIterator.next();
    }
}
