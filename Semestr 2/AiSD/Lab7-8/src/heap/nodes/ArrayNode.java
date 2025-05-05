package heap.nodes;

import heap.visitors.NodeVisitor;
import heap.visitors.SiftUpVisitor;

import java.util.ArrayList;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    private void enlargeArray() {
        T[] newArray = (T[]) new Comparable[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void addValue(T value) {
        if (size == array.length) {
            enlargeArray();
        }
        array[size++] = value;
    }

    @Override
    public T getLastValue() {
        T returnValue = array[size - 1];
        array[--size] = null;
        return returnValue;
    }

    private Integer pathSum(List<T> path) {
        List<Integer> intPath = (ArrayList<Integer>) path;
        return intPath.stream().reduce(0, Integer::sum);
    }

    public List<T> getGreatestPath(int index) {
        List<T> list = new ArrayList<>();
        list.add(array[index]);
        if (2*index + 1 >= size) {
            return list;
        } else if (2*index + 2 >= size) {
            list.addAll(getGreatestPath(2*index + 1));
            return list;
        } else {
            List<T> leftPath = getGreatestPath(2*index + 1);
            List<T> rightPath = getGreatestPath(2*index + 2);

            List<T> biggerSum = pathSum(leftPath).compareTo(pathSum(rightPath)) > 0 ? leftPath : rightPath;
            list.addAll(biggerSum);
            return list;
        }
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
