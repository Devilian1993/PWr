package BST;

import java.util.Comparator;

public class BST<T> {
    private Node<T> root;
    private int size;
    private final Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private Node<T> findNode(Node<T> node, T key) {
        if (node.getValue() == null || node.getValue().equals(key)) {
            return node;
        }

        if (comparator.compare(node.getValue(), key) <= 0) {
            return findNode(node.getRightChild(), key);
        } else {
            return findNode(node.getLeftChild(), key);
        }
    }
}
