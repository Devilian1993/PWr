import java.util.Iterator;

public class FibonacciGenerator implements Iterator<Integer> {
    private int prevPrevN;
    private int prevN;
    private int count;

    public FibonacciGenerator() {
        prevPrevN = 1;
        prevN = 1;
        count = 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        if (count < 3) {
            count++;
            return 1;
        } else {
            int returnValue = prevPrevN + prevN;
            prevPrevN = prevN;
            prevN = returnValue;

            count++;

            return returnValue;
        }
    }
}
