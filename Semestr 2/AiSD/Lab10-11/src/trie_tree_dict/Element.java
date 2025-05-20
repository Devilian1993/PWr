package trie_tree_dict;

public abstract class Element<T> {
    private T value;

    public Element() {
        this.value = null;
    }

    public Element(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
