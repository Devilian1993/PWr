import stack.*;


public class VelosoTraversableStack<E> extends ArrayStack<E> {
    private Integer cursor;

    public VelosoTraversableStack() {
        super();
        cursor = 0;
    }

    public VelosoTraversableStack(int size) {
        super(size);
        cursor = 0;
    }

    @Override
    public E pop() throws EmptyStackException {
        E returnValue =  super.pop();
        cursor = topIndex - 1;
        return returnValue;
    }

    @Override
    public void push(E e) throws FullStackException {
        super.push(e);
        cursor = topIndex - 1;
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return array[cursor];
    }

    public void down() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        if (cursor != 0) {
            cursor--;
        }

        if (cursor == 0) {
            System.out.println("End of stack reached");
        }
    }

    public void cursorTop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        cursor = topIndex - 1;
    }
}
