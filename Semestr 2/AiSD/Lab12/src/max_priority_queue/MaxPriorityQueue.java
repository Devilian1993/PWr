package max_priority_queue;

import binomial_heap.MaxBinomialHeap;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPriorityQueue<P, V> {
    private MaxBinomialHeap<Wrapper<V, P>> heap;
    private int size;
    private final WrapperComparator<V, P> comparator;

    public MaxPriorityQueue(Comparator<P> priorityComparator) {
        this.comparator = new WrapperComparator<>(priorityComparator);
        this.heap = new MaxBinomialHeap<>(comparator);
        this.size = 0;
    }

    public void enqueue(P priority, V element) {
        heap.insert(new Wrapper<>(element, priority));
        size++;
    }

    public V dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        return heap.extractMax().value;
    }

    public V peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return heap.getMax().value;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
