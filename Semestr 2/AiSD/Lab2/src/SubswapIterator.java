import java.util.ArrayList;
import java.util.Iterator;

public class SubswapIterator<E> implements Iterator {

    Iterator<Integer> baseIterator;
    ArrayList<E> tempList;
    int K;

    public SubswapIterator(Iterator<Integer> baseIterator, int K) {
        this.baseIterator = baseIterator;
        this.tempList = new ArrayList<>();
    }

    private void setupList(){
        int temp = 0;
        while (baseIterator.hasNext() && temp < K) {
            
        }
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    @Override
    public Object next() {
        return 0;
    }
}
