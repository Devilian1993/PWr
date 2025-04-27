package heap.nodes;

import heap.visitors.NodeVisitor;
import heap.visitors.SiftUpVisitor;

public class ArrayNode<T extends Comparable<T>> implements Node<T> {
    private T[] array;
    private int size;
    private final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public ArrayNode() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public T getSubheapRootValue() {
        return array[0];
    }

    @Override
    public void setSubheapRootValue(T value) {
        array[0] = value;
    }

    @Override
    public void addValue(T value) {
        array[size++] = value;
    }

    @Override
    public T getLastValue() {
        T returnValue = array[size - 1];
        array[--size] = null;
        return returnValue;
    }

    @Override
    public void accept(NodeVisitor<T> visitor) {
        visitor.visitArrayNode(this);
    }

    @Override
    public void internalSiftUp(SiftUpVisitor<T> visitor) {
        T valueBeforeSiftUp = size > 1 ? getSubheapRootValue() : null;
        arraySiftUp();
        visitor.setContinueSiftUp(valueBeforeSiftUp == null || !valueBeforeSiftUp.equals(getSubheapRootValue()));

        visitor.setChildNode(this);
        visitor.continueSiftUp();
    }

    @Override
    public boolean checkForRemoval() {
        return size == 0;
    }

    @Override
    public void remove(TreeNode<T> parentNode) {
        if (parentNode.getRightChild() == this) {
            parentNode.setRightChild(null);
        } else {
            parentNode.setLeftChild(null);
        }
    }

    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void arraySiftUp() {
        int index = size - 1;
        T currentValue = array[index];

        while (index > 0) {
            int parentIndex = index / 2;

            if (currentValue.compareTo(array[parentIndex]) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public void internalSiftDown() {
        int index = 0;

        while (index < size && index*2 + 1 < size) {
            int childIndex = index*2 + 1 == size - 1 || array[index*2 + 1].compareTo(array[index*2 + 2]) > 0 ? index * 2 + 1 : index*2 + 2;
            if (array[index].compareTo(array[childIndex]) < 0) {
                swap(index, childIndex);
                index = childIndex;
            } else {
                break;
            }
        }
    }
}
