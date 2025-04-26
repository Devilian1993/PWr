import java.util.ArrayList;

public class TreeNode<T> extends AbstractNode<T> {
    private int index;
    private TreeNode<T> leftNode;
    private TreeNode<T> rightNode;

    public TreeNode(T value) {
        super(value);
        this.leftNode = null;
        this.rightNode = null;
    }

    public TreeNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }
}
