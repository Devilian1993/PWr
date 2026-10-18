import binomial_heap.MaxBinomialHeap;
import max_priority_queue.MaxPriorityQueue;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        MaxBinomialHeap<Integer> heap = new MaxBinomialHeap<>(comparator);


    }
}