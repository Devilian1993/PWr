package heap.visitors;

import heap.nodes.*;

public class SiftDownVisitor<T extends Comparable<T>> implements NodeVisitor<T> {
    @Override
    public void visitTreeNode(TreeNode<T> node) {
        T value = node.getSubheapRootValue();

        Node<T> childNode = null;

        if (node.getLeftChild() != null) {
            childNode = node.getRightChild() == null || node.getLeftChild().getSubheapRootValue().compareTo(node.getRightChild().getSubheapRootValue()) > 0
                    ? node.getLeftChild() : node.getRightChild();
        }

        if (childNode != null && value.compareTo(childNode.getSubheapRootValue()) < 0) {
            node.setSubheapRootValue(childNode.getSubheapRootValue());
            childNode.setSubheapRootValue(value);
            childNode.accept(this);
        }
    }

    @Override
    public void visitArrayNode(ArrayNode<T> node) {
        node.internalSiftDown();
    }
}
