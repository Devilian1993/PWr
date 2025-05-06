package BST;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<T> {
    private Node<T> root;
    private final Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private Node<T> findNode(Node<T> node, T key) {
        if (node == null) {
            return null;
        }

        if (comparator.compare(node.getValue(), key) == 0) {
            return node;
        }

        if (comparator.compare(node.getValue(), key) < 0) {
            return findNode(node.getRightChild(), key);
        } else {
            return findNode(node.getLeftChild(), key);
        }
    }

    private Node<T> findNodeIterative(T key) {
        Node<T> currentNode = root;

        while(currentNode != null && comparator.compare(currentNode.getValue(), key) != 0) {
            if (comparator.compare(currentNode.getValue(), key) < 0) {
                currentNode = currentNode.getRightChild();
            } else {
                currentNode = currentNode.getLeftChild();
            }
        }

        return currentNode;
    }

    private Node<T> findMin(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.hasLeftChild()) {
            return findMin(node.getLeftChild());
        } else {
            return node;
        }
    }

    public T findMin() {
        if (root == null) {
            return null;
        }
        return findMin(root).getValue();
    }

    private Node<T> findMax(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node.hasRightChild()) {
            return findMax(node.getRightChild());
        } else {
            return node;
        }
    }


    public T findMax() {
        if (root == null) {
            return null;
        }
        return findMax(root).getValue();
    }

    private Stack<Node<T>> getParents(Node<T> node) {
        Stack<Node<T>> parents = new Stack<>();

        Node<T> currentParent = root;

        while(currentParent != node) {
            parents.push(currentParent);
            if (comparator.compare(currentParent.getValue(), node.getValue()) > 0) {
                currentParent = currentParent.getLeftChild();
            } else {
                currentParent = currentParent.getRightChild();
            }
        }

        return parents;
    }

    private Node<T> findSuccessor(Node<T> node) {
        if (node.hasRightChild()) {
            Node<T> successor = node.getRightChild();
            while (successor.hasLeftChild()) {
                successor = successor.getLeftChild();
            }
            return successor;
        } else {
            Stack<Node<T>> parents = getParents(node);
            Node<T> currentNode = node;
            Node<T> parent = null;

            while(!parents.isEmpty()) {
                parent = parents.pop();
                if (parent.getLeftChild() == currentNode) {
                    return parent;
                } else {
                    currentNode = parent;
                }
            }

            return null;
        }
    }

    public T findSuccessor(T key) {
        if (root == null) {
            return null;
        }
        Node<T> successor = findSuccessor(findNode(root, key));
        if (successor == null) {
            return null;
        }

        return successor.getValue();
    }

    public void insert(T key) {
        Node<T> parentNode = root;

        if (root == null) {
            root = new Node<>(key);
            return;
        }

        while (true) {
            if (comparator.compare(parentNode.getValue(), key) > 0) {
                if (parentNode.hasLeftChild()) {
                    parentNode = parentNode.getLeftChild();
                } else {
                    parentNode.setLeftChild(new Node<>(key));
                    return;
                }
            } else if (comparator.compare(parentNode.getValue(), key) == 0) {
                return;
            } else {
                if (parentNode.hasRightChild()) {
                    parentNode = parentNode.getRightChild();
                } else {
                    parentNode.setRightChild(new Node<>(key));
                    return;
                }
            }
        }
    }

    public void delete(T key) {
        Node<T> nodeToDelete = findNodeIterative(key);

        if (nodeToDelete == null) {
            return;
        }

        Node<T> parentNode = nodeToDelete.getParent(root, comparator);

        if (!nodeToDelete.hasAnyChildren()) {
            if (nodeToDelete == root) {
                root = null;
                return;
            }
            if (nodeToDelete.isLeftChild(parentNode, comparator)) {
                parentNode.setLeftChild(null);
            } else {
                parentNode.setRightChild(null);
            }
        } else if (nodeToDelete.hasExactlyOneChild()) {
            if (nodeToDelete == root) {
                root = nodeToDelete.getOnlyChild();
            } else {
                if (nodeToDelete.isLeftChild(parentNode, comparator)) {
                    parentNode.setLeftChild(nodeToDelete.getOnlyChild());
                } else {
                    parentNode.setRightChild(nodeToDelete.getOnlyChild());
                }
            }
        } else {
            Node<T> successor = findSuccessor(nodeToDelete);
            T newValue = successor.getValue();
            delete(successor.getValue());
            nodeToDelete.setValue(newValue);
        }
    }

    private void preOrderWalk(Visitor<T> visitor, Node<T> node) {
        if (node == null) {
            return;
        }

        node.accept(visitor);
        preOrderWalk(visitor, node.getLeftChild());
        preOrderWalk(visitor, node.getRightChild());
    }

    public void preOrderWalk() {
        Visitor<T> visitor = new PreOrderWalkVisitor<T>();
        preOrderWalk(visitor, root);
    }
}
