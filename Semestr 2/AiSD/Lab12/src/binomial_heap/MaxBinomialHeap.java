package binomial_heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MaxBinomialHeap<T> {
    private Node<T> head;
    private final Comparator<T> comparator;

    public MaxBinomialHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T getMax() {
        Node<T> currentNode = head;
        T max = null;

        while (currentNode != null) {
            if (max != null && comparator.compare(currentNode.getValue(), max) > 0) {
                max = currentNode.getValue();
            } else if (max == null) {
                max = currentNode.getValue();
            }
            currentNode = currentNode.getSibling();
        }

        return max;
    }

    private Node<T> getMaxNode() {
        Node<T> currentNode = head;
        Node<T> max = null;

        while (currentNode != null) {
            if (max != null && comparator.compare(currentNode.getValue(), max.getValue()) > 0) {
                max = currentNode;
            } else if (max == null) {
                max = currentNode;
            }
            currentNode = currentNode.getSibling();
        }

        return max;
    }

    private Node<T> reverseSiblingList(Node<T> headToReverse) {
        Node<T> current = headToReverse;
        Node<T> prev = null;
        Node<T> nextNode = null;

        while (current != null) {
            nextNode = current.getSibling();
            current.setSibling(prev);
            current.setParent(null);
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    public T extractMax() {
        if (head == null) {
            return null;
        }

        Node<T> maxRoot = getMaxNode();
        T maxValue = maxRoot.getValue();

        Node<T> current = head;
        Node<T> previous = null;

        while (current != maxRoot) {
            previous = current;
            current = current.getSibling();
        }

        if (previous == null) {
            head = maxRoot.getSibling();
        } else {
            previous.setSibling(maxRoot.getSibling());
        }

        Node<T> childrenHead = maxRoot.getChild();
        maxRoot.setChild(null);

        Node<T> reversedChildrenHead = reverseSiblingList(childrenHead);

        MaxBinomialHeap<T> childrenHeap = new MaxBinomialHeap<>(this.comparator);
        childrenHeap.head = reversedChildrenHead;

        MaxBinomialHeap<T> resultHeap = MaxBinomialHeap.heapUnion(this, childrenHeap);
        this.head = resultHeap.head;

        return maxValue;
    }

    private Node<T> binomialLink(Node<T> node1, Node<T> node2) {
        if (node1.getDegree() != node2.getDegree()) {
            throw new UnsupportedOperationException("Operacja linkHeaps działa tylko na kopcach o tym samym stopniu");
        }
        Node<T> newParent = comparator.compare(node1.getValue(), node2.getValue()) >= 0 ? node1 : node2;
        Node<T> newChild = comparator.compare(node1.getValue(), node2.getValue()) < 0 ? node1 : node2;
        newChild.setParent(newParent);
        newChild.setSibling(newParent.getChild());
        newParent.setChild(newChild);
        newParent.incrementDegree();

        return newParent;
    }

    private static <T> Node<T> binomialMerge(MaxBinomialHeap<T> heap1, MaxBinomialHeap<T> heap2) {
        Node<T> dummyHead = new Node<>(null);
        Node<T> currentMergedTail = dummyHead;

        Node<T> current1 = heap1.head;
        Node<T> current2 = heap2.head;

        while (current1 != null && current2 != null) {
            if (current1.getDegree() <= current2.getDegree()) {
                currentMergedTail.setSibling(current1);
                current1 = current1.getSibling();
            } else {
                currentMergedTail.setSibling(current2);
                current2 = current2.getSibling();
            }
            currentMergedTail = currentMergedTail.getSibling();
        }

        if (current1 != null) {
            currentMergedTail.setSibling(current1);
        }
        else if (current2 != null) {
            currentMergedTail.setSibling(current2);
        }

        return dummyHead.getSibling();
    }

    public static <T> MaxBinomialHeap<T> heapUnion(MaxBinomialHeap<T> heap1, MaxBinomialHeap<T> heap2) {
        if (!heap1.comparator.equals(heap2.comparator)) {
            throw new UnsupportedOperationException("Kopce mają różne komparatory!");
        }

        MaxBinomialHeap<T> newHeap = new MaxBinomialHeap<>(heap1.comparator);
        newHeap.head = binomialMerge(heap1, heap2);

        heap1.head = null;
        heap2.head = null;

        if (newHeap.head == null) {
            return newHeap;
        }

        Node<T> previous = null;
        Node<T> current = newHeap.head;
        Node<T> next = newHeap.head.getSibling();

        while (next != null) {
            if (current.getDegree() != next.getDegree() ||
                    (next.getSibling() != null && next.getSibling().getDegree() == current.getDegree())) {
                previous = current;
                current = next;
            } else {
                if (newHeap.comparator.compare(current.getValue(), next.getValue()) >= 0) {
                    current.setSibling(next.getSibling());
                    current = newHeap.binomialLink(current, next);
                } else {
                    if (previous == null) {
                        newHeap.head = next;
                    } else {
                        previous.setSibling(next);
                    }
                    current = newHeap.binomialLink(next, current);
                }
            }
            next = current.getSibling();
        }

        return newHeap;
    }

    public void insert(T value) {
        MaxBinomialHeap<T> newHeap = new MaxBinomialHeap<>(this.comparator);
        newHeap.head = new Node<>(value);

        this.head = heapUnion(this, newHeap).head;
    }
}
