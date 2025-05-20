package trie_tree_dict;

abstract class Element<T> {
    private final Character key;
    private T value;
    private boolean isEndOfWord;

    public Element(char key) {
        this.key = key;
        this.value = null;
        this.isEndOfWord = false;
    }

    public Element(char key, T value) {
        this.key = key;
        this.value = value;
        this.isEndOfWord = true;
    }

    public char getKey() {
        return key;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public abstract Element<T> getChild(char c);

    public abstract void setChild(char c, Element<T> child);

    public abstract void removeChild(char c);

    public abstract void removeChild(Element<T> child);

    public abstract boolean isLeaf();

    public abstract boolean hasAnyDescendants();

    public abstract boolean hasChild(Character c);
}
