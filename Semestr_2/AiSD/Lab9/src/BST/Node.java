package BST;

import java.util.Comparator;

class Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public boolean hasBothChildren() {
        return leftChild != null && rightChild != null;
    }

    public boolean hasAnyChildren() {
        return leftChild != null || rightChild != null;
    }

    public boolean hasExactlyOneChild() {
        return ( leftChild == null && rightChild != null ) || ( leftChild != null && rightChild == null );
    }

    public Node<T> getParent(Node<T> root, Comparator<T> comparator) {
        if (this == root) {
            return null;
        }

        Node<T> currentNode = root;

        while (currentNode != null && currentNode.leftChild != this && currentNode.rightChild != this) {
            if (comparator.compare(currentNode.value, this.value) > 0) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }

        return currentNode;
    }

    public boolean isLeftChild(Node<T> parent, Comparator<T> comparator) {
        return parent.hasLeftChild() && comparator.compare(parent.value, this.value) > 0;
    }

    public Node<T> getOnlyChild() {
        return leftChild != null ? leftChild : rightChild;
    }

    public void accept(Visitor<T> visitor) {
        visitor.visit(this);
    }
}
