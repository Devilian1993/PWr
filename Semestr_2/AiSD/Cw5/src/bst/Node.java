package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Node<T> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean hasAnyChildren() {
        return left != null || right != null;
    }

    public boolean hasTwoChildren() {
        return left != null && right != null;
    }

    public boolean hasExactlyOneChild() {
        return hasAnyChildren() && !hasTwoChildren();
    }

    public Node<T> getParent(Node<T> root, Comparator<T> comparator) {
        if (this == root) {
            return null;
        }

        Node<T> currentNode = root;

        while (currentNode != null && currentNode.left != this && currentNode.right != this) {
            if (comparator.compare(currentNode.value, this.value) > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return currentNode;
    }

    public Node<T> getOnlyChild() {
        if (hasTwoChildren()) {
            throw new UnsupportedOperationException("You can't get only child of a node that has two children");
        }
        return left != null ? left : right;
    }

    public boolean isLeftChild(Node<T> parent, Comparator<T> comparator) {
        return parent.hasLeft() && comparator.compare(parent.value, this.value) > 0;
    }

    public boolean hasExactlyOneBrother(Node<T> root, Comparator<T> comparator) {
        return getParent(root, comparator).hasTwoChildren();
    }

    public List<Node<T>> getChildren() {
        List<Node<T>> children = new ArrayList<>();
        if (right != null) {
            children.add(right);
        }
        if (left != null) {
            children.add(left);
        }

        return children;
    }
}
