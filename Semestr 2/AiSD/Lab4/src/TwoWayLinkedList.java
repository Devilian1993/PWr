public class TwoWayLinkedList<E> implements IList<E> {
    private final TwoWayListElement<E> frontSentinel;
    private final TwoWayListElement<E> rearSentinel;
    private TwoWayListElement<E> head;
    private TwoWayListElement<E> tail;

    public TwoWayLinkedList() {
        frontSentinel = new TwoWayListElement<>(null);
        rearSentinel = new TwoWayListElement<>(null);
        frontSentinel.setSentinel(true);
        rearSentinel.setSentinel(true);

        frontSentinel.setNextSecondElement(frontSentinel);
        frontSentinel.setPreviousElement(frontSentinel);
        rearSentinel.setNextSecondElement(rearSentinel);
        rearSentinel.setPreviousElement(rearSentinel);

        head = null;
        tail = null;
    }

    @Override
    public boolean add(E value) {
        if (head == null) {
            head = new TwoWayListElement<>(value);
            tail = head;

            head.setPreviousElement(frontSentinel);
            head.setNextSecondElement(rearSentinel);
            frontSentinel.setNextSecondElement(rearSentinel);
            rearSentinel.setPreviousElement(head);
        } else {
            TwoWayListElement<E> newElement = new TwoWayListElement<>(value);

            newElement.setPreviousElement(tail);
            tail.getPreviousElement().setNextSecondElement(newElement);
            tail = newElement;
            tail.setNextSecondElement(rearSentinel);
            rearSentinel.setPreviousElement(tail);

        }

        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index == 0) {
            add(value);
        } else {
            TwoWayListElement<E> newElement = new TwoWayListElement<>(value);
            TwoWayListElement<E> previousElement = getElement(index - 1);
            TwoWayListElement<E> nextElement = previousElement.getNextElement();

            previousElement.setNextSecondElement(nextElement);
            newElement.setPreviousElement(previousElement);

            newElement.setNextSecondElement(nextElement.getNextElement());
            nextElement.setPreviousElement(newElement);
        }
    }

    @Override
    public void clear() {
        frontSentinel.setNextSecondElement(frontSentinel);
        frontSentinel.setPreviousElement(frontSentinel);
        rearSentinel.setNextSecondElement(rearSentinel);
        rearSentinel.setPreviousElement(rearSentinel);

        head = null;
        tail = null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        TwoWayListElement<E> element = frontSentinel.getNextElement();
        int counter = 0;

        while(element != frontSentinel && counter < index && element != rearSentinel) {
            counter++;
            element = element.getNextElement();
        }

        if(element == frontSentinel || element == rearSentinel) {
            throw new IndexOutOfBoundsException();
        }

        return element.getValue();
    }

    private TwoWayListElement<E> getElement(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        TwoWayListElement<E> element = frontSentinel.getNextElement();
        int counter = 0;

        while(element != frontSentinel && counter < index && element != rearSentinel){
            counter++;
            element = element.getNextElement();
        }

        if(element == frontSentinel || element == rearSentinel) {
            throw new IndexOutOfBoundsException();
        }

        return element;
    }

    @Override
    public E set(int index, E value) {

        TwoWayListElement<E> element = getElement(index);
        element.setValue(value);

        return element.getValue();
    }

    @Override
    public int indexOf(E element) {
        TwoWayListElement<E> currentElement = frontSentinel.getNextElement();
        int index = 0;

        while(currentElement != frontSentinel && currentElement != element && currentElement != rearSentinel){
            index++;
            currentElement = currentElement.getNextElement();
        }

        if(currentElement == frontSentinel || currentElement == rearSentinel) {
            return -1;
        }

        return index;
    }

    @Override
    public boolean isEmpty() {
        return frontSentinel.getNextElement() == frontSentinel;
    }

    @Override
    public E remove(int index) {
        TwoWayListElement<E> removedElement = getElement(index);
        TwoWayListElement<E> previous = removedElement.getPreviousElement();
        TwoWayListElement<E> next = removedElement.getNextElement();

        previous.setNextSecondElement(next.getNextElement());
        next.setPreviousElement(previous);

        if (removedElement == head) {
            head = frontSentinel.getNextSecondElement();
        }

        if (removedElement == tail) {
            tail = rearSentinel.getPreviousElement();
        }

        return removedElement.getValue();
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);

        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    @Override
    public int size() {
        int size = 0;
        TwoWayListElement<E> currentElement = head;

        while(!isEmpty() && currentElement != rearSentinel) {
            size++;
            currentElement = currentElement.getNextElement();
        }

        return size;
    }

    public TwoWayListElement<E> getHead() {
        return head;
    }

    public TwoWayListElement<E> getTail() {
        return tail;
    }
}
