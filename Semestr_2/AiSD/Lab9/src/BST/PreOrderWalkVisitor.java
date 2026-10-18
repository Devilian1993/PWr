package BST;

class PreOrderWalkVisitor<T> implements Visitor<T> {
    @Override
    public void visit(Node<T> node) {
        System.out.println(node.getValue() + " ");
    }
}
