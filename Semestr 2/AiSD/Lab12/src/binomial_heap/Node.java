package binomial_heap;

class Node<T> {
    private T value;
    private int degree;
    private Node<T> parent;
    private Node<T> child;
    private Node<T> sibling;

    public Node(T value) {
        this.value = value;
        this.degree = 0;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void incrementDegree() {
        degree++;
    }

    public void decrementDegree() {
        degree--;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getChild() {
        return child;
    }

    public void setChild(Node<T> child) {
        this.child = child;
    }

    public Node<T> getSibling() {
        return sibling;
    }

    public void setSibling(Node<T> sibling) {
        this.sibling = sibling;
    }
}
