public class TwoWayListElement<E> {
    private E value;
    private TwoWayListElement<E> nextSecondElement;
    private TwoWayListElement<E> previousElement;
    private boolean sentinel;

    public TwoWayListElement(E value) {
        this.value = value;
        this.sentinel = false;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TwoWayListElement<E> getNextElement() {
        if (nextSecondElement.previousElement == this && nextSecondElement.isSentinel()) {
            return nextSecondElement;
        } else {
            return nextSecondElement.previousElement;
        }
    }

    public TwoWayListElement<E> getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(TwoWayListElement<E> previousElement) {
        this.previousElement = previousElement;
    }

    public TwoWayListElement<E> getNextSecondElement() {
        return nextSecondElement;
    }

    public void setNextSecondElement(TwoWayListElement<E> nextSecondElement) {
        this.nextSecondElement = nextSecondElement;
    }

    public boolean isSentinel() {
        return sentinel;
    }

    public void setSentinel(boolean sentinel) {
        this.sentinel = sentinel;
    }
}
