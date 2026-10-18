package trie_tree_dict;

import java.util.*;
import java.util.stream.Collectors;

public class TrieDictionary<V> {
    private Element<V> root;
    private List<V> LARGEST_PATH;

    public TrieDictionary() {
        this.root = null;
        this.LARGEST_PATH = new ArrayList<>();
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
                    current.incrementChildrenCount();
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

        if (!stack.isEmpty()) {
            stack.peek().decrementChildrenCount();
        }

        V returnValue = current.getValue();
        current.setEndOfWord(false);
        current.setValue(null);

        if (current.getNumberOfChildren() != 0) {
            return returnValue;
        }

        Element<V> parent = stack.pop();
        parent.removeChild(current);
        current = parent;

        while (!stack.isEmpty() && current.getNumberOfChildren() < 2 && !current.isEndOfWord()) {
            parent = stack.pop();
            parent.removeChild(current);
            parent.decrementChildrenCount();
            current = parent;
        }

          if (current != null && current.isEndOfWord() && !stack.isEmpty() && current.getNumberOfChildren() < 1) {
            stack.pop().setChild(current.getKey(), new Leaf<>(current.getKey(), current.getValue()));
        }

        if (current == root && !current.hasAnyDescendants() && !root.isEndOfWord()) {
            root = null;
        }

        return returnValue;
    }

    private List<String> _longestEmptyPath(Element<V> currentElement, List<String> currentPath) {
        if (currentElement == null || currentElement.isEndOfWord()) {
            currentPath.clear();
        } else {
            currentPath.add(currentElement.getKeyString());
        }


        List<Element<V>> descendants = currentElement.getChildren();

        if (descendants == null) {
            return null;
        }

        descendants = descendants.stream().filter(Objects::nonNull).filter(Element::hasAnyDescendants).collect(Collectors.toCollection(ArrayList::new));

        if (descendants.isEmpty()) {
            return currentPath;
        }

        List<String> largestPath = new ArrayList<>();
        for (Element<V> descendant : descendants) {
            List<String> path = _longestEmptyPath(descendant, currentPath);
            if (path != null && path.size() > largestPath.size()) {
                largestPath = new ArrayList<>(path);
                //currentPath.clear();
            }
            currentPath.clear();
        }
        return largestPath;
    }

    public String longestEmptyPath() {
        List<String> path =  _longestEmptyPath(root, new ArrayList<>());

        StringBuilder pathString = new StringBuilder();

        if (path == null) {
            return null;
        }

        for (String s : path) {
            pathString.append(s);
        }

        return pathString.toString();
    }
}
