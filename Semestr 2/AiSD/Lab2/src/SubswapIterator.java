import java.util.*;

public class SubswapIterator<E> implements Iterator<E> {

    Iterator<E> baseIterator;
    int K;
    Stack<E> stack;

    public SubswapIterator(Iterator<E> baseIterator, int K) {
        this.baseIterator = baseIterator;
        this.K = K;
        this.stack = new Stack<>();
        fillStack();
    }

    private void fillStack(){
        for (int i = 0; i < K && baseIterator.hasNext(); i++) {
            stack.push(baseIterator.next());
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || baseIterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (stack.isEmpty()){
            fillStack();
        }

        return stack.pop();
    }
}
