import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import BST.BST;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BSTTests {
    private BST<Integer> bst;
    @BeforeEach
    void setUp() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        bst = new BST<>(comparator);
    }

    @Test
    @DisplayName("Empty BST")
    void testEmptyBST() {
        assertThrows(NoSuchElementException.class, () -> bst.delete(1));
        assertThrows(NoSuchElementException.class, bst::findMax);
        assertThrows(NoSuchElementException.class, bst::findMin);
        assertThrows(NoSuchElementException.class, () -> bst.findSuccessor(1));

        bst.insert(1);
        assertEquals(1, bst.findMin());
        assertEquals(1, bst.findMax());
        bst.insert(2);
        assertEquals(2, bst.findMax());
    }

    @Test
    @DisplayName("Standard BST")
    void testStandardBST() {
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);
        bst.insert(30);
        bst.insert(15);
        bst.insert(8);
        bst.insert(4);

        assertEquals(30, bst.findMax());
        assertEquals(4, bst.findMin());
        assertEquals(5, bst.findSuccessor(4));

        bst.delete(10);
        assertEquals(15, bst.findSuccessor(8));

        bst.delete(5);
        assertEquals(8, bst.findSuccessor(4));
    }

    @Test
    @DisplayName("Linked list BST")
    void testLinkedListBST() {
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(40);
        bst.insert(50);
        bst.insert(60);

        assertEquals(60, bst.findMax());
        assertEquals(10, bst.findMin());

        bst.delete(30);
        assertEquals(40, bst.findSuccessor(20));
    }
}
