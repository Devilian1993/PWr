package bst;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BST<T> {
    private Node<T> root;
    private final Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private Node<T> findNode(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (comparator.compare(node.getValue(), value) == 0) {
            return node;
        }

        if (comparator.compare(node.getValue(), value) < 0) {
            return findNode(node.getRight(), value);
        } else {
            return findNode(node.getLeft(), value);
        }
    }

    private void _insert(T value, Node<T> node) {
        int comparison = comparator.compare(node.getValue(), value);

        if (comparison > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(value));
            } else {
                _insert(value, node.getLeft());
            }
        } else if (comparison < 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(value));
            } else {
                _insert(value, node.getRight());
            }
        }
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            _insert(value, root);
        }
    }

    private Stack<Node<T>> getParents(Node<T> node) {
        Stack<Node<T>> parents = new Stack<>();

        Node<T> currentParent = root;

        while(currentParent != node) {
            parents.push(currentParent);
            if (comparator.compare(currentParent.getValue(), node.getValue()) > 0) {
                currentParent = currentParent.getLeft();
            } else {
                currentParent = currentParent.getRight();
            }
        }

        return parents;
    }

    private Node<T> _findSuccessor(Node<T> node) {
        if (node.hasRight()) {
            Node<T> successor = node.getRight();
            while (successor.hasLeft()) {
                successor = successor.getLeft();
            }
            return successor;
        } else {
            Stack<Node<T>> parents = getParents(node);
            Node<T> currentNode = node;
            Node<T> parent = null;

            while(!parents.isEmpty()) {
                parent = parents.pop();
                if (parent.getLeft() == currentNode) {
                    return parent;
                } else {
                    currentNode = parent;
                }
            }

            throw new NoSuchElementException();
        }
    }

    public T findSuccessor(T value) {
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node<T> successor = _findSuccessor(findNode(root, value));

        return successor.getValue();
    }

    public void delete(T value) {
        Node<T> nodeToDelete = findNode(root, value);

        if (nodeToDelete == null) {
            throw new NoSuchElementException();
        }

        Node<T> parentNode = nodeToDelete.getParent(root, comparator);

        if (!nodeToDelete.hasAnyChildren()) {
            if (nodeToDelete == root) {
                root = null;
                return;
            }
            if (nodeToDelete.isLeftChild(parentNode, comparator)) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (nodeToDelete.hasExactlyOneChild()) {
            if (nodeToDelete == root) {
                root = nodeToDelete.getOnlyChild();
            } else {
                if (nodeToDelete.isLeftChild(parentNode, comparator)) {
                    parentNode.setLeft(nodeToDelete.getOnlyChild());
                } else {
                    parentNode.setRight(nodeToDelete.getOnlyChild());
                }
            }
        } else {
            Node<T> successor = _findSuccessor(nodeToDelete);
            T newValue = successor.getValue();
            delete(successor.getValue());
            nodeToDelete.setValue(newValue);
        }
    }

    private void _preOrderWalk(Node<T> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getValue() + " ");
        _preOrderWalk(node.getLeft());
        _preOrderWalk(node.getRight());
    }

    public void preOrderWalk() {
        System.out.println("PRE-ORDER WALK");
        _preOrderWalk(root);
        System.out.println();
    }

    private void _inOrderWalk(Node<T> node) {
        if (node == null) {
            return;
        }

        _inOrderWalk(node.getLeft());
        System.out.print(node.getValue() + " ");
        _inOrderWalk(node.getRight());
    }

    public void inOrderWalk() {
        System.out.println("IN-ORDER WALK");
        _inOrderWalk(root);
        System.out.println();
    }

    private void _postOrderWalk(Node<T> node) {
        if (node == null) {
            return;
        }

        _postOrderWalk(node.getLeft());
        _postOrderWalk(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    public void postOrderWalk() {
        System.out.println("POST-ORDER WALK");
        _postOrderWalk(root);
        System.out.println();
    }

    //private void _getAllNodeList(List<Node<T>> nodeList, Node<T> node) {
    //    nodeList.add(node);
    //    if (node.hasLeft()) {
    //        _getAllNodeList(nodeList, node.getLeft());
    //    }
    //    if (node.hasRight()) {
    //        _getAllNodeList(nodeList, node.getRight());
    //    }
    //}
//
    //private List<Node<T>> getAllNodeList() {
    //    List<Node<T>> nodes = new LinkedList<>();
    //    _getAllNodeList(nodes, root);
//
    //    return nodes;
    //}

    private void _countNodes(Node<T> node, AtomicInteger count) {
        if (node == null) {
            return;
        }

        if (node == root) {
            count.incrementAndGet();
        }

        if (node.hasLeft()) {
            count.incrementAndGet();
            _countNodes(node.getLeft(), count);
        }
        if (node.hasRight()) {
            count.incrementAndGet();
            _countNodes(node.getRight(), count);
        }
    }

    public Integer countNodes() {
        AtomicInteger count = new AtomicInteger(0);
        _countNodes(root, count);
        return count.get();
    }

    private Integer _countHeight(Node<T> node) {
        if (node == null) {
            return 0;
        }

        Integer leftHeight = _countHeight(node.getLeft());
        Integer rightHeight = _countHeight(node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public Integer countHeight() {
        return _countHeight(root);
    }

    private void _countEvenNodes(Node<T> node, AtomicInteger count) {
        if (node == null) {
            return;
        }

        if (node == root && (Integer) node.getValue() % 2 == 0) {
            count.incrementAndGet();
        }

        if (node.hasLeft()) {
            if ((Integer) node.getLeft().getValue() % 2 == 0) {
                count.incrementAndGet();
            }
            _countEvenNodes(node.getLeft(), count);
        }
        if (node.hasRight()) {
            if ((Integer) node.getRight().getValue() % 2 == 0) {
                count.incrementAndGet();
            }
            _countEvenNodes(node.getRight(), count);
        }
    }

    public Integer countEvenNodes() {
        AtomicInteger count = new AtomicInteger(0);
        _countEvenNodes(root, count);
        return count.get();
    }

    private void _countSingleChildNodes(Node<T> node, AtomicInteger count) {
        if (node == null) {
            return;
        }

        if (node == root && node.hasExactlyOneChild()) {
            count.incrementAndGet();
        }

        if (node.hasLeft()) {
            if (node.getLeft().hasExactlyOneChild()) {
                count.incrementAndGet();
            }
            _countSingleChildNodes(node.getLeft(), count);
        }
        if (node.hasRight()) {
            if (node.getRight().hasExactlyOneChild()) {
                count.incrementAndGet();
            }
            _countSingleChildNodes(node.getRight(), count);
        }
    }

    public Integer countSingleChildNodes() {
        AtomicInteger count = new AtomicInteger(0);
        _countSingleChildNodes(root, count);
        return count.get();
    }

    private void _countSingleBrotherNodes(Node<T> node, AtomicInteger count) {
        if (node == null) {
            return;
        }

        if (node.hasLeft()) {
            if (node.getLeft().hasExactlyOneBrother(root, comparator)) {
                count.incrementAndGet();
            }
            _countSingleBrotherNodes(node.getLeft(), count);
        }
        if (node.hasRight()) {
            if (node.getRight().hasExactlyOneBrother(root, comparator)) {
                count.incrementAndGet();
            }
            _countSingleBrotherNodes(node.getRight(), count);
        }
    }

    public Integer countSingleBrotherNodes() {
        AtomicInteger count = new AtomicInteger(0);
        _countSingleBrotherNodes(root, count);
        return count.get();
    }

    private List<Node<T>> _longestOneChildPath(Node<T> currentElement, List<Node<T>> currentPath) {
        if (currentElement == null || !currentElement.hasExactlyOneChild()) {
            currentPath.clear();
        } else {
            currentPath.add(currentElement);
        }


        List<Node<T>> children = currentElement.getChildren();

        children = children.stream().filter(Objects::nonNull).filter(Node::hasAnyChildren).collect(Collectors.toCollection(ArrayList::new));

        if (children.isEmpty()) {
            return currentPath;
        }

        List<Node<T>> largestPath = new ArrayList<>();
        for (Node<T> descendant : children) {
            List<Node<T>> path = _longestOneChildPath(descendant, currentPath);
            if (path != null && path.size() > largestPath.size()) {
                largestPath = new ArrayList<>(path);
            }
            currentPath.clear();
        }
        return largestPath;
    }

    public T longestOneChildSequenceStart() {
        List<Node<T>> path = _longestOneChildPath(root, new ArrayList<>());

        return path.isEmpty() ? null : path.getFirst().getValue();
    }

    public void printTreeByLevel() {
        if (root != null) {
            Queue<Node<T>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                for (int i = 0; i < queueSize; i++) {
                    Node<T> node = queue.poll();
                    System.out.print(node.getValue() + " ");

                    if (node.getLeft() != null) {
                        queue.add(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        queue.add(node.getRight());
                    }
                }
            }
        }
    }
}
