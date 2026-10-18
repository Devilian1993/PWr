package heap.visitors;

import heap.nodes.*;

public class SiftUpVisitor<T extends Comparable<T>> implements NodeVisitor<T> {
    private boolean continueSiftUp;
    private Node<T> childNode;

    public SiftUpVisitor() {
        continueSiftUp = true;
    }

    @Override
    public void visitTreeNode(TreeNode<T> node) {
        T parentValue = node.getSubheapRootValue();
        T childValue = childNode.getSubheapRootValue();

        if(parentValue.compareTo(childValue) < 0) {
            node.addValue(childValue);
            childNode.setSubheapRootValue(parentValue);
            childNode = node;
        } else {
            continueSiftUp = false;
        }
    }

    @Override
    public void visitArrayNode(ArrayNode<T> node) {
        throw new UnsupportedOperationException("Visitor nie powinien odwiedzać ArrayNode - powinien on zostać przygotowany wcześniej");
    }

    public boolean continueSiftUp() {
        return continueSiftUp;
    }

    public void setContinueSiftUp(boolean continueSiftUp) {
        this.continueSiftUp = continueSiftUp;
    }

    public void setChildNode(Node<T> childNode) {
        this.childNode = childNode;
    }
}
