package trie_tree_dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Node<T> extends Element<T> {
    private final Element<T>[] children;

    @SuppressWarnings("unchecked")
    public Node(Character key) {
        super(key);
        this.children = (Element<T>[]) new Element[Utils.ASCII_SIZE];
    }

    @SuppressWarnings("unchecked")
    public Node(char key, T value) {
        super(key, value);
        this.children = (Element<T>[]) new Element[Utils.ASCII_SIZE];
    }

    public void addChild(char c, Element<T> child) {
        children[Utils.getASCII(c)] = child;
    }

    @Override
    public void removeChild(char c) {
        children[Utils.getASCII(c)] = null;
    }

    @Override
    public void removeChild(Element<T> child) {
        children[Utils.getASCII(child.getKey())] = null;
    }

    @Override
    public Element<T> getChild(char c) {
        return children[Utils.getASCII(c)];
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean hasAnyDescendants() {
        //return this.getNumberOfChildren() > 0;
        for (Element<T> child : Arrays.stream(children).filter(Objects::nonNull).toArray(Element[]::new)) {
            if (child.isEndOfWord() || child.hasAnyDescendants()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setChild(char c, Element<T> child) {
        children[Utils.getASCII(c)] = child;
    }

    @Override
    public boolean hasChild(Character c) {
        return children[Utils.getASCII(c)] != null;
    }

    @Override
    public List<Element<T>> getChildren() {
        List<Element<T>> list = new ArrayList<Element<T>>(children.length);

        for (Element<T> child : children) {
            if (child != null) {
                list.add(child);
            }
        }
        return list;
    }
}
