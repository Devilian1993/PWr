package trie_tree_dict;

import java.util.Stack;

public class TrieDictionary<V> {
    private Element<V> root;

    public TrieDictionary() {
        this.root = null;
    }

    public V insert(String key, V value) {
        if (root == null && key.isEmpty()) {
            root = new Leaf<>(Character.MIN_VALUE, value);
            root.setEndOfWord(true);
            return null;
        }

        if (root == null) {
            root = new Node<>(Character.MIN_VALUE);
        }

        char[] chars = key.toCharArray();
        Element<V> current = root;
        Element<V> prev = null;

        for (int i = 0; i < chars.length; i++) {
            if (current.isLeaf()) {
                current = current.isEndOfWord() ? new Node<>(chars[i-1], current.getValue()) : new Node<>(chars[i]);
                if (prev != null) {
                    prev.setChild(current.getKey(), current);
                }
            }
            if (!current.hasChild(chars[i])) {
                if (i == chars.length - 1) {
                    ((Node<V>) current).addChild(chars[i], new Leaf<>(chars[i]));
                } else {
                    ((Node<V>) current).addChild(chars[i], new Node<>(chars[i]));
                }
            }
            prev = current;
            current = current.getChild(chars[i]);
        }

        V returnValue = current.getValue();
        current.setValue(value);
        current.setEndOfWord(true);

        return returnValue;
    }

    public V search(String key) {
        char[] chars = key.toCharArray();
        Element<V> current = root;

        for (int i = 0; i < chars.length; i++) {
            if (current == null || current.isLeaf()) {
                return null;
            }
            current = current.getChild(chars[i]);
        }

        if (current == null || !current.isEndOfWord()) {
            return null;
        }

        return current.getValue();
    }

    public V remove(String key) {
        Stack<Element<V>> stack = new Stack<>();
        char[] chars = key.toCharArray();
        Element<V> current = root;

        for (int i = 0; i < chars.length; i++) {
            if (current == null || current.isLeaf()) {
                return null;
            }
            stack.push(current);
            current = current.getChild(chars[i]);
        }

        if (current == null || !current.isEndOfWord()) {
            return null;
        }

        V returnValue = current.getValue();
        current.setEndOfWord(false);
        current.setValue(null);

        if (current.hasAnyDescendants()) {
            return returnValue;
        }

        current = stack.pop();

        while (!stack.isEmpty() && !current.hasAnyDescendants() && !current.isEndOfWord()) {
            Element<V> parent = stack.pop();

            parent.removeChild(current);
            current = parent;
        }

        if (current != null && current.isEndOfWord() && !stack.isEmpty() && !current.hasAnyDescendants()) {
            stack.pop().setChild(current.getKey(), new Leaf<>(current.getKey(), current.getValue()));
        }

        if (current == root && !current.hasAnyDescendants() && !root.isEndOfWord()) {
            root = null;
        }

        return returnValue;
    }
}
