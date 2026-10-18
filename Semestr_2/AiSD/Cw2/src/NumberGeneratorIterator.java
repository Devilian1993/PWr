import java.util.Iterator;

public class NumberGeneratorIterator implements Iterator<Integer> {
    private int n;

    public NumberGeneratorIterator(int n) {
        this.n = n;
    }

    public boolean hasNext() {
        return true;
    }

    public Integer next() {
        int prevN = n;
        n++;
        return prevN;
    }
}
