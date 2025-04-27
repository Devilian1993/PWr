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
        if (H < 0) {
            throw new IllegalArgumentException("Argument H musi być >= 0");
        }
        this.H = H + 1;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
    }

    private List<Node<T>> getPathToRoot(int index) {
        List<Node<T>> path = new ArrayList<>();
        String binaryIndex = Integer.toBinaryString(index).substring(1);

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
        pathToRoot.getFirst().internalSiftUp(visitor);

        for (int i = 1; i < pathToRoot.size() && visitor.continueSiftUp(); i++) {
            Node<T> parentNode = pathToRoot.get(i);
            parentNode.accept(visitor);
        }
    }

    @Override
    public void add(T element) {
        List<Node<T>> pathToRoot = getPathToRoot(size + 1);
        Node<T> newElementNode = pathToRoot.getFirst();
        newElementNode.addValue(element);
        size++;
        siftUp(pathToRoot);
    }

    public void siftDown() {
        SiftDownVisitor<T> visitor = new SiftDownVisitor<>();
        root.accept(visitor);
    }

    @Override
    public T maximum() {
        if (size == 0) {
            return null;
        }

        T returnValue = root.getSubheapRootValue();

        List<Node<T>> pathFromRoot = getPathToRoot(size).reversed();
        Node<T> lastNode = pathFromRoot.getLast();
        root.setSubheapRootValue(lastNode.getLastValue());
        size--;

        if (lastNode.checkForRemoval()) {
            if (lastNode != root) {
                lastNode.remove((TreeNode<T>) pathFromRoot.get(pathFromRoot.size() - 2));
            } else {
                root = null;
                return returnValue;
            }
        }

        siftDown();

        return returnValue;
    }
}
