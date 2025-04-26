public abstract class AbstractNode<T> {
    private T value;

    public AbstractNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
