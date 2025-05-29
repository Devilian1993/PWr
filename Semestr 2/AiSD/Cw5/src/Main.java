import bst.BST;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void bstSetup(BST<Integer> bst) {
        bst.insert(20);
        bst.insert(7);
        bst.insert(10);
        bst.insert(25);
        bst.insert(4);
        bst.insert(1);
        bst.insert(2);
        bst.insert(12);
        bst.insert(30);
        //bst.delete(12);
        //bst.delete(1);
        //bst.delete(20);
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(Comparator.<Integer>naturalOrder());
        bstSetup(bst);

        bst.preOrderWalk();
        bst.postOrderWalk();
        bst.inOrderWalk();

        System.out.println("NUMBER OF NODES: ");
        System.out.println(bst.countNodes());
        System.out.println("TREE HEIGHT: ");
        System.out.println(bst.countHeight());
        System.out.println("NUMBER OF NODES WITH EVEN VALUE: ");
        System.out.println(bst.countEvenNodes());
        System.out.println("NUMBER OF NODES WITH EXACTLY ONE CHILD: ");
        System.out.println(bst.countSingleChildNodes());
        System.out.println("NUMBER OF NODES WITH EXACTLY ONE BROTHER: ");
        System.out.println(bst.countSingleBrotherNodes());
        System.out.println("START OF LARGEST ONE CHILD SEQUENCE: ");
        System.out.println(bst.longestOneChildSequenceStart());
        System.out.println("PRINT TREE BY LEVELS");
        bst.printTreeByLevel();
    }
}