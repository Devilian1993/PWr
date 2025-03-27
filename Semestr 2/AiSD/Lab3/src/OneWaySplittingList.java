public class OneWaySplittingList<E> implements IList<E> {
    ListElement<E> head;

    public OneWaySplittingList() {
        this.head = null;
    }

    @Override
    public boolean add(E e) {
        ListElement<E> newElement = new ListElement<>(e);

        if (head == null) {
            head = newElement;
            newElement.setIndex(0);
            return true;
        }

        ListElement<E> tail = head;
        while(tail.getNextElement()!=null) {
            tail = tail.getNextElement();
        }
        tail.setNextElement(newElement);
        newElement.setIndex(tail.getIndex()+1);

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 ) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            add(element);
        } else {
            ListElement<E> newElement = new ListElement<>(element);
            ListElement<E> previousElement = get(index - 1);

            newElement.setNextElement(previousElement.getNextElement());
            previousElement.setNextElement(newElement);
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(E data) {
        return indexOf(data)>=0;
    }

    @Override
    public ListElement<E> get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            ListElement<E> currentElement = head;
            while(index > 0 && currentElement != null){
                index--;
                if (currentElement.getSplitElement() != null && index >= currentElement.getSplitElement().getIndex()) {
                    index -= currentElement.getSplitElement().getIndex() - 1;
                    currentElement = currentElement.getSplitElement();
                } else {
                    currentElement = currentElement.getNextElement();
                }
            }

            if (currentElement ==null)
                throw new IndexOutOfBoundsException();

            ListElement<E> splitElement = head;

            while (splitElement.getSplitElement() != null) {
                splitElement = splitElement.getSplitElement();
            }
            if (!splitElement.equals(currentElement)) {
                splitElement.setSplitElement(currentElement);
            }

            return currentElement;
        }
    }

    @Override
    public ListElement<E> set(int index, E data) {
        ListElement<E> actElem= get(index);
        E elemData=actElem.getValue();
        actElem.setValue(data);
        return actElem;
    }

    @Override
    public int indexOf(E data) {
        int pos=0;
        ListElement<E> actElem = head;
        while(actElem!=null)
        {
            if(actElem.getValue().equals(data))
                return pos;
            pos++;
            actElem=actElem.getNextElement();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E remove(int index) {
        if(index<0 || head==null) {
            throw new IndexOutOfBoundsException();
        }

        if(index==0) {
            E retValue=head.getValue();
            head=head.getNextElement();
            return retValue;
        }

        ListElement<E> actElem = get(index-1);

        if(actElem.getNextElement() == null) {
            throw new IndexOutOfBoundsException();
        }

        E retValue=actElem.getNextElement().getValue();
        actElem.setNextElement(actElem.getNextElement().getNextElement());
        return retValue;
    }

    @Override
    public boolean remove(E value) {
        if(head==null) {
            return false;
        }

        if(head.getValue().equals(value)){
            head=head.getNextElement();
            return true;
        }

        ListElement<E> actElem=head;
        while(actElem.getNextElement()!=null && !actElem.getNextElement().getValue().equals(value)) {
            actElem = actElem.getNextElement();
        }

        if(actElem.getNextElement()==null) {
            return false;
        }

        actElem.setNextElement(actElem.getNextElement().getNextElement());
        return true;
    }

    @Override
    public int size() {
        int counter = 0;
        ListElement<E> currentElement = head;

        while (currentElement != null) {
            counter++;
            currentElement = currentElement.getNextElement();
        }

        return counter;
    }
}
