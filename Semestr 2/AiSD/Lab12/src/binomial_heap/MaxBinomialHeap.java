package binomial_heap;

import java.util.Comparator;

public class MaxBinomialHeap<T> {
    Node<T> head;
    Comparator<T> comparator;

    public MaxBinomialHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T getMax() {
        Node<T> currentNode = head;
        T max = null;

        while (currentNode != null) {
            if (comparator.compare(currentNode.getValue(), max) > 0) {
                max = currentNode.getValue();
            }
            currentNode = currentNode.getSibling();
        }

        return max;
    }

    public void mergeHeaps(MaxBinomialHeap<T> heap1, MaxBinomialHeap<T> heap2) {

    }
}
