package heap.nodes;

import heap.visitors.NodeVisitor;
import heap.visitors.SiftUpVisitor;

import java.util.List;

public interface Node<T extends Comparable<T>> {
    T getSubheapRootValue();
    T getLastValue();
    void setSubheapRootValue(T value);
    void addValue(T value);
    void accept(NodeVisitor<T> visitor);
    void internalSiftUp(SiftUpVisitor<T> visitor);
    boolean checkForRemoval();
    void remove(TreeNode<T> parentNode);
    List<T> getGreatestPath(int index);
}

