package heap.nodes;

import heap.visitors.NodeVisitor;
import heap.visitors.SiftUpVisitor;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends Comparable<T>> implements Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private List<T> greatestList;

    public TreeNode() {
        this.leftChild = null;
        this.rightChild = null;
        this.greatestList = new ArrayList<>();
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

    private Integer pathSum(List<T> path) {
        List<Integer> intPath = (ArrayList<Integer>) path;
        return intPath.stream().reduce(0, Integer::sum);
    }

    public List<T> getGreatestPath(int index) {
        List<T> list = new ArrayList<>();
        list.add(value);
        if (leftChild == null) {
            return list;
        } else if (rightChild == null) {
            list.addAll(leftChild.getGreatestPath(index));
            return list;
        } else {
            List<T> leftPath = leftChild.getGreatestPath(index);
            List<T> rightPath = rightChild.getGreatestPath(index);

            List<T> biggerSum = pathSum(leftPath).compareTo(pathSum(rightPath)) > 0 ? leftPath : rightPath;
            list.addAll(biggerSum);
            return list;
        }
    }
}


