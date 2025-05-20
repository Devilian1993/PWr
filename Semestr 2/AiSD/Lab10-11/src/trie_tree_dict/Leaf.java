package trie_tree_dict;

class Leaf<T> extends Element<T> {
    public Leaf(Character c, T value) {
        super(c, value);
    }

    public Leaf(Character c) {
        super(c);
    }


    @Override
    public Element<T> getChild(char c) {
        return null;
    }

    @Override
    public void setChild(char c, Element<T> child) {
        return;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public void removeChild(char c) {
        return;
    }

    @Override
    public void removeChild(Element<T> child) {
        return;
    }

    @Override
    public boolean hasAnyDescendants() {
        return false;
    }

    @Override
    public boolean hasChild(Character c) {
        return false;
    }
}
