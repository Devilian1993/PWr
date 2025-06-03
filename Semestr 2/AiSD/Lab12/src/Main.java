import binomial_heap.MaxBinomialHeap;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        MaxBinomialHeap<Integer> binomialHeap = new MaxBinomialHeap<>(comparator);

        binomialHeap.insert(8);
        binomialHeap.insert(7);
        binomialHeap.insert(6);
        binomialHeap.insert(5);
        binomialHeap.insert(4);
        binomialHeap.insert(3);
        binomialHeap.insert(2);
        binomialHeap.insert(1);

        System.out.println(binomialHeap.extractMax());
    }
}