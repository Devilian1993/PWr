package heap.visitors;

import heap.nodes.ArrayNode;
import heap.nodes.TreeNode;

public class SiftDownVisitor<T extends Comparable<T>> implements NodeVisitor<T> {
    @Override
    public void visitTreeNode(TreeNode<T> node) {

    }

    @Override
    public void visitArrayNode(ArrayNode<T> node) {

    }
}
