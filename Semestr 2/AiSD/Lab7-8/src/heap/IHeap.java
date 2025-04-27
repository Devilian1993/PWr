package heap;

public interface IHeap<T extends Comparable<T>> {
    void clear();
    void add(T element);
    T maximum();
}

