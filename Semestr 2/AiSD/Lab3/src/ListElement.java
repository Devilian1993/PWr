public class ListElement<E> {
    private E value;
    private ListElement<E> nextElement;
    private ListElement<E> splitElement;

    private int index;

    public ListElement(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public ListElement<E> getNextElement() {
        return nextElement;
    }

    public void setNextElement(ListElement<E> nextElement) {
        this.nextElement = nextElement;
    }

    public ListElement<E> getSplitElement() {
        return splitElement;
    }

    public void setSplitElement(ListElement<E> splitElement) {
        this.splitElement = splitElement;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
