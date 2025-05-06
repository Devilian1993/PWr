import BST.BST;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BST<Integer> bst = new BST<>(comparator);

        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(25);
        bst.insert(4);
        bst.insert(5);
        bst.insert(1);

        bst.preOrderWalk();
    }
}