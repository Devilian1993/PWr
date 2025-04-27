package heap.nodes;

import heap.visitors.NodeVisitor;
import heap.visitors.SiftUpVisitor;

public class TreeNode<T extends Comparable<T>> implements Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;
    public TreeNode() {
        this.leftChild = null;
        this.rightChild = null;
    }

    public TreeNode(T value) {
        this.leftChild = null;
        this.rightChild = null;
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

    @Override
    public void setSubheapRootValue(T value) {
        this.value = value;
    }

    @Override
    public void addValue(T value) {
        this.value = value;
    }

    @Override
    public T getSubheapRootValue() {
        return value;
    }

    @Override
    public T getLastValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor<T> visitor) {
        visitor.visitTreeNode(this);
    }

    @Override
    public void internalSiftUp(SiftUpVisitor<T> visitor) {
        visitor.setChildNode(this);
    }

    @Override
    public boolean checkForRemoval() {
        return true;
    }

    @Override
    public void remove(TreeNode<T> parentNode) {
        if (parentNode.getRightChild() == this) {
            parentNode.setRightChild(null);
        } else {
            parentNode.setLeftChild(null);
        }
    }
}


