import BST.BST;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BST<Integer> bst = new BST<>(comparator);

        bst.delete(1);
    }
}