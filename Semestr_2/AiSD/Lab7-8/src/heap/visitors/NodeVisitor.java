package heap.visitors;

import heap.nodes.*;

public interface NodeVisitor<T extends Comparable<T>> {
    void visitTreeNode(TreeNode<T> node);
    void visitArrayNode(ArrayNode<T> node);
}
