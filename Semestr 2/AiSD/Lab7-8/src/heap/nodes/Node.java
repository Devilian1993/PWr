package heap.nodes;

import heap.visitors.NodeVisitor;
import heap.visitors.SiftUpVisitor;

public interface Node<T extends Comparable<T>> {
    T getValue();
    void setValue(T value);
    void addValue(T value);
    void accept(NodeVisitor<T> visitor);
    boolean siftUpSetup(SiftUpVisitor<T> visitor);
}

