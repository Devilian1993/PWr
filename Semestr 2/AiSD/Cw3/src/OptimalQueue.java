import queue.IQueue;
import queue.EmptyQueueException;
import queue.FullQueueException;

import java.util.Stack;

public class OptimalQueue<E> implements IQueue<E> {

    private static final int DEFAULT_CAPACITY = 16;
    int capacity;
    Stack<E> inputStack;
    Stack<E> outputStack;

    public OptimalQueue() {
        capacity = DEFAULT_CAPACITY;
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public OptimalQueue(int capacity) {
        this.capacity = capacity;
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    @Override
    public boolean isEmpty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return size() == capacity;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }

        return outputStack.pop();
    }

    @Override
    public void enqueue(E elem) throws FullQueueException {
        if (isFull()) {
            throw new FullQueueException();
        }

        inputStack.push(elem);
    }

    @Override
    public int size() {
        return inputStack.size() + outputStack.size();
    }

    @Override
    public E first() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }

        E returnValue = outputStack.pop();
        outputStack.push(returnValue);

        return returnValue;
    }
}
