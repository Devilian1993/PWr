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
    public T getValue() {
        return array[0];
    }

    @Override
    public void setValue(T value) {
        array[0] = value;
    }

    @Override
    public void addValue(T value) {
        array[size++] = value;
    }

    @Override
    public void accept(NodeVisitor<T> visitor) {
        visitor.visitArrayNode(this);
    }

    @Override
    public boolean siftUpSetup(SiftUpVisitor<T> visitor) {
        T valueBeforeSiftUp = size != 0 ? getValue() : null;
        internalSiftUp();
        visitor.setContinueSiftUp(valueBeforeSiftUp == null || !valueBeforeSiftUp.equals(getValue()));

        visitor.setChildNode(this);
        return visitor.continueSiftUp();
    }

    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void internalSiftUp() {
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
}
