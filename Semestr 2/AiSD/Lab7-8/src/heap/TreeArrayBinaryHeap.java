package heap;

import heap.nodes.*;
import heap.visitors.*;

import java.util.ArrayList;
import java.util.List;

public class TreeArrayBinaryHeap<T extends Comparable<T>> implements IHeap<T> {

    private TreeNode<T> root;
    private final int H;
    private int size;

    public TreeArrayBinaryHeap(int H) {
        if (H < 1) {
            throw new IllegalArgumentException("Argument H musi być >= 1");
        }
        this.H = H;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
    }

    private List<Node<T>> getPathToRoot() {
        List<Node<T>> path = new ArrayList<>();
        String binaryIndex = Integer.toBinaryString(size + 1).substring(1);

        TreeNode<T> currentNode = root;
        int depth = 0;

        if (currentNode == null) {
            root = new TreeNode<>();
            path.add(root);
            return path;
        }

        path.add(currentNode);

        while (!binaryIndex.isEmpty() && depth < H - 1) {
            if (binaryIndex.charAt(0) == '1') {
                if (currentNode.getRightChild() == null) {
                    currentNode.setRightChild(new TreeNode<>());
                }
                currentNode = (TreeNode<T>) currentNode.getRightChild();
            } else {
                if (currentNode.getLeftChild() == null) {
                    currentNode.setLeftChild(new TreeNode<>());
                }
                currentNode = (TreeNode<T>) currentNode.getLeftChild();
            }
            depth++;
            binaryIndex = binaryIndex.substring(1);
            path.add(currentNode);
        }

        if (depth == H - 1 && !binaryIndex.isEmpty()) {
            if (binaryIndex.charAt(0) == '1') {
                if (currentNode.getRightChild() == null) {
                    currentNode.setRightChild(new ArrayNode<>());
                }
                path.add(currentNode.getRightChild());
            } else {
                if (currentNode.getLeftChild() == null) {
                    currentNode.setLeftChild(new ArrayNode<>());
                }
                path.add(currentNode.getLeftChild());
            }
        }

        return path.reversed();
    }

    private void siftUp(List<Node<T>> pathToRoot) {

        SiftUpVisitor<T> visitor = new SiftUpVisitor<>();
        pathToRoot.getFirst().siftUpSetup(visitor);

        for (int i = 1; i < pathToRoot.size() && visitor.continueSiftUp(); i++) {
            Node<T> parentNode = pathToRoot.get(i);
            parentNode.accept(visitor);
        }
    }

    @Override
    public void add(T element) {
        List<Node<T>> pathToRoot = getPathToRoot();
        Node<T> newElementNode = pathToRoot.getFirst();
        newElementNode.addValue(element);
        size++;
        siftUp(pathToRoot);
    }

    @Override
    public T maximum() {
        if (size == 0) {
            return null;
        }

        T returnValue = root.getValue();
        size--;

        return null;
    }
}
