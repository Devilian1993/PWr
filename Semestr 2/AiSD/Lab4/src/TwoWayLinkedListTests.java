import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoWayLinkedListTests {

    private IList<Object> list;

    @BeforeEach
    void createEmptyList() {
        list = new TwoWayLinkedList<>();
    }

    @Test
    @DisplayName("Getting an element from an empty list should throw an exception")
    void getFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
    }

    @Test
    @DisplayName("Removing an element from an empty list should throw an exception")
    void removeFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(10));
    }

    @Test
    @DisplayName("Adding and removing an element from an empty list should result in an empty list")
    void addAndRemoveFromEmptyList() {
        list.add(0);
        list.remove(0);

        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Testing list size")
    void testSize() {
        assertEquals(0, list.size());

        list.add(null);
        assertEquals(1, list.size());

        list.add(null);
        list.add(null);
        list.add(null);

        assertEquals(4, list.size());
    }

    @Test
    @DisplayName("Testing getting elements")
    void testGettingElements() {
        list.add(0);
        list.add(1);
        list.add(2);

        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    @DisplayName("Testing removing and getting elements")
    void testRemovingAndGettingElements() {
        list.add(0);
        list.add(1);
        list.add(2);

        assertEquals(1, list.get(1));

        list.remove(1);

        assertEquals(2, list.get(1));

        list.remove(0);

        assertEquals(2, list.get(0));

        list.remove(0);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    @DisplayName("Adding elements of different types should not throw errors")
    void testDifferentTypes() {
        assertDoesNotThrow(() -> list.add(0));
        assertDoesNotThrow(() -> list.add(null));
        assertDoesNotThrow(() -> list.add("String"));
        assertDoesNotThrow(() -> list.add(new IndexOutOfBoundsException()));
    }

    @Test
    @DisplayName("Accessing relative elements")
    void testAccessingSecondNextElement() {
        TwoWayListElement<Integer> first = new TwoWayListElement<>(1);
        TwoWayListElement<Integer> second = new TwoWayListElement<>(2);
        TwoWayListElement<Integer> third = new TwoWayListElement<>(3);

        first.setNextSecondElement(third);
        second.setPreviousElement(first);
        third.setPreviousElement(second);

        assertEquals(3, first.getNextSecondElement().getValue());
        assertEquals(2, first.getNextElement().getValue());

        assertEquals(2, third.getPreviousElement().getValue());
        assertEquals(1, second.getPreviousElement().getValue());
    }
}
