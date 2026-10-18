package trie_tree_dict;

import java.util.List;

abstract class Element<T> {
    private final Character key;
    private T value;
    private boolean isEndOfWord;
    private int numberOfChildren;

    public Element(char key) {
        this.key = key;
        this.value = null;
        this.isEndOfWord = false;
        this.numberOfChildren = 0;
    }

    public Element(char key, T value) {
        this.key = key;
        this.value = value;
        this.isEndOfWord = true;
        this.numberOfChildren = 0;
    }

    public Character getKey() {
        return key;
    }

    public String getKeyString() {
        return key.toString();
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

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void incrementChildrenCount() {
        numberOfChildren++;
    }

    public void decrementChildrenCount() {
        numberOfChildren--;
    }

    abstract public List<Element<T>> getChildren();
}
