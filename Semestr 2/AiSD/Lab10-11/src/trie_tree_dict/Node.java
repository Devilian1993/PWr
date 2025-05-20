package trie_tree_dict;

import java.util.ArrayList;

public class Node<T> extends Element<T> {
    private final ArrayList<Element<T>> children;

    public Node() {
        this.children = new ArrayList<>(256);
    }

    public void addChild(char c) {
        children.add(Utils.getASCII(c), new);
    }
}
