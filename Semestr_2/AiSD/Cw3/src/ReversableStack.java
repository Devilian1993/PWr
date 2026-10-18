import stack.ArrayStack;
import stack.EmptyStackException;
import stack.FullStackException;

public class ReversableStack<E> extends ArrayStack<E> {
    public ReversableStack() {
        super();
    }

    public ReversableStack(int size) {
        super(size);
    }

    public void reverse() throws EmptyStackException, FullStackException {
        ArrayStack<E> tempStack1 = new ArrayStack<>();
        ArrayStack<E> tempStack2 = new ArrayStack<>();

        while (!isEmpty()) {
            tempStack1.push(this.pop());
        }

        while (!tempStack1.isEmpty()) {
            tempStack2.push(tempStack1.pop());
        }

        while (!tempStack2.isEmpty()) {
            this.push(tempStack2.pop());
        }
    }
}
