import heap.TreeArrayBinaryHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class HeapTests {

    @Test
    @DisplayName("Testy pustego kopca")
    void emptyHeapTest() {
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(1);

        assertThrows(NoSuchElementException.class, heap::maximum);
        heap.add(0);
        assertEquals(0, heap.maximum());
        assertThrows(NoSuchElementException.class, heap::maximum);
    }

    private void size9HeapAssertions(TreeArrayBinaryHeap<Integer> heap) {
        assertAll(() -> assertEquals(8, heap.maximum()),
                () -> assertEquals(7, heap.maximum()),
                () -> assertEquals(6, heap.maximum()),
                () -> assertEquals(5, heap.maximum()),
                () -> assertEquals(4, heap.maximum()),
                () -> assertEquals(3, heap.maximum()),
                () -> assertEquals(2, heap.maximum()),
                () -> assertEquals(1, heap.maximum()),
                () -> assertEquals(0, heap.maximum())
        );
    }

    @Test
    @DisplayName("Dodawanie elementów w odwrotnej kolejności")
    void reverseElements() {
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(0);

        heap.add(0);
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(6);
        heap.add(7);
        heap.add(8);

        size9HeapAssertions(heap);
    }

    @Test
    @DisplayName("Dodawanie elementów w poprawnej kolejności")
    void orderedElements() {
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(1);

        heap.add(8);
        heap.add(7);
        heap.add(6);
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        heap.add(0);

        size9HeapAssertions(heap);
    }

    @Test
    @DisplayName("Dodawanie elementów w losowej kolejności")
    void randomElements() {
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(1);

        heap.add(5);
        heap.add(7);
        heap.add(1);
        heap.add(2);
        heap.add(0);
        heap.add(8);
        heap.add(6);
        heap.add(3);
        heap.add(4);

        size9HeapAssertions(heap);
    }

    @Test
    @DisplayName("Dodawanie duplikatów")
    void duplicateElements() {
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(1);

        heap.add(1);
        heap.add(1);
        heap.add(1);
        heap.add(2);
        heap.add(2);
        heap.add(2);
        heap.add(3);
        heap.add(3);
        heap.add(3);

        assertAll(() -> assertEquals(3, heap.maximum()),
                () -> assertEquals(3, heap.maximum()),
                () -> assertEquals(3, heap.maximum()),
                () -> assertEquals(2, heap.maximum()),
                () -> assertEquals(2, heap.maximum()),
                () -> assertEquals(2, heap.maximum()),
                () -> assertEquals(1, heap.maximum()),
                () -> assertEquals(1, heap.maximum()),
                () -> assertEquals(1, heap.maximum())
        );
    }
}
