import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SubswapIterator<E> implements Iterator<E> {

    Iterator<E> baseIterator;
    int K;
    Stack<E> stack;

    public SubswapIterator(Iterator<E> baseIterator, int K) {
        this.baseIterator = baseIterator;
        this.K = K;
        this.stack = new Stack<>();
    }

    private void fillStack(){
        for (int i = 0; i < K && baseIterator.hasNext(); i++) {
            stack.push(baseIterator.next());
        }
    }

    @Override
    public boolean hasNext() {
        return baseIterator.hasNext() || !stack.isEmpty();
    }

    @Override
    public E next() {
        if (stack.isEmpty()){
            fillStack();
        }

        return stack.pop();
    }
}
