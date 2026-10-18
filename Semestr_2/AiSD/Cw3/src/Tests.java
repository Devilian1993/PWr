import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stack.EmptyStackException;
import stack.FullStackException;
import queue.EmptyQueueException;
import queue.FullQueueException;
import calculator.Calculator;

import static org.junit.jupiter.api.Assertions.*;


public class Tests {
    @Test
    @DisplayName("Zadanie 1 - Veloso's traversable stack")
    void zadanie1() throws FullStackException, EmptyStackException {
        VelosoTraversableStack<Integer> stack = new VelosoTraversableStack<>();

        assertThrows(EmptyStackException.class, stack::peek);

        stack.push(0);

        assertEquals(0, stack.peek());

        stack.push(1);
        assertEquals(1, stack.peek());

        stack.down();
        assertEquals(0, stack.peek());

        stack.cursorTop();

        assertEquals(1, stack.peek());

        stack.down();
        stack.push(2);

        assertEquals(2, stack.peek());
    }

    @Test
    @DisplayName("Zadanie 2 - odwracanie stosu")
    void zadanie2() throws FullStackException, EmptyStackException {
        ReversableStack<Integer> stack = new ReversableStack<>();

        stack.push(0);
        stack.push(1);
        stack.push(2);

        stack.reverse();

        assertEquals(0, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(2, stack.pop());
    }

    @Test
    @DisplayName("Zadanie 3 - implementacja kolejki za pomocą dwóch stosów")
    void zadanie3() throws FullQueueException, EmptyQueueException {
        OptimalQueue<Integer> queue = new OptimalQueue<>(3);

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(3, queue.size());

        assertThrows(FullQueueException.class, () -> queue.enqueue(4));

        assertEquals(0, queue.dequeue());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());

        assertEquals(0, queue.size());

        assertThrows(EmptyQueueException.class, queue::dequeue);
    }

    @Test
    @DisplayName("Zadanie 6 - obliczanie wyrażenia za pomocą ONP")
    void zadanie6() {
        Calculator calculator = new Calculator();

        assertEquals(6, calculator.calculate("2 + 2 * 2"));
        assertEquals(8, calculator.calculate("(2 + 2) * 2"));
        assertEquals(8, calculator.calculate("2 * 3 + 2"));
        assertEquals(10, calculator.calculate("2^3 + 2"));
    }
}
