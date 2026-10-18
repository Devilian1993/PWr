import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OneWaySplittingListTests {

    OneWaySplittingList<Object> list;
    @BeforeEach
    void setupEmptyList() {
        list = new OneWaySplittingList<>();
    }

    @Test
    @DisplayName("Testy na pustej liście")
    void emptyListTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertEquals(0, list.size());

        list.add(null);

        assertDoesNotThrow(() -> list.get(0));
    }

    @Test
    @DisplayName("Testy na liście o parzystej dlugosci")
    void evenListTest() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(4, list.size());
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));

        list.add(4);
        assertEquals(4, list.get(4));
        assertEquals(5, list.size());

        list.remove(2);

        assertEquals(4, list.get(3));
    }

    @Test
    @DisplayName("Testy na liście o nieparzystej dlugości")
    void oddListTest() {
        list.add(0);
        list.add(1);
        list.add(2);

        assertEquals(3, list.size());
        assertEquals(2, list.get(2));
        assertEquals(1, list.get(1));

        list.add(3);

        assertEquals(4, list.size());
        assertEquals(3, list.get(3));

        list.remove(1);

        assertEquals(2, list.get(1));

        list.remove(1);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    @DisplayName("Testy na liście jednoelementowej")
    void singleElementListTest() {
        list.add(0);

        assertEquals(1, list.size());
        assertEquals(0, list.get(0));
    }

    @Test
    @DisplayName("Testy różnych typów")
    void differentTypesTest() {
        list.add(0);
        list.add("String");
        list.add(null);
        list.add(new ArrayList<>());

        assertEquals(4, list.size());
        assertEquals("String", list.get(1));
        assertEquals(null, list.get(2));
    }

    @Test
    @DisplayName("Test odwracania listy")
    void reverseListTest() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.get(3);

        list.reverseList();

        assertEquals(2, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(0, list.get(2));
        assertEquals(4, list.get(3));
        assertEquals(3, list.get(4));

        list.remove(0);
        assertEquals(1, list.get(0));
    }
}
