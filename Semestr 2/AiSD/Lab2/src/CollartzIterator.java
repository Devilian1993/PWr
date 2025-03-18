import java.util.Iterator;

public class CollartzIterator implements Iterator<Integer>, Iterable<Integer> {

    Integer n;

    public CollartzIterator(Integer n) {
        this.n = n;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int prevN = n;
        if (n % 2 == 0) {
            n = n / 2;
        } else {
            n = 3*n + 1;
        }
        return prevN;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }
}
