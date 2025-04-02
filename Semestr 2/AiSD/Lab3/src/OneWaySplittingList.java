import java.util.ArrayList;
import java.util.List;

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

        while (tail.getSplitElement() != null) {
            tail = tail.getSplitElement();
        }

        while(tail.getNextElement()!=null) {
            tail = tail.getNextElement();
        }

        tail.setNextElement(newElement);
        newElement.setIndex(size() - 1);

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
            ListElement<E> previousElement = getElement(index - 1);

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
    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            ListElement<E> previousElement = null;
            ListElement<E> currentElement = head;
            ListElement<E> splitHead = head;
            int counter = 0;

            do {
                if (splitHead != null && splitHead.getIndex() == index) {
                    return splitHead.getValue();
                }

                if (splitHead != null) {
                    splitHead = splitHead.getSplitElement();
                }

            } while (splitHead != null && splitHead.getIndex() != index);

            splitHead = head;

            while(counter != index && currentElement != null){
                counter++;
                previousElement = currentElement;
                currentElement = currentElement.getNextElement();

                if(currentElement == null && splitHead.getSplitElement() != null) {
                    currentElement = splitHead.getSplitElement();
                    splitHead = currentElement;
                }
            }

            if (currentElement ==null)
                throw new IndexOutOfBoundsException();

            ListElement<E> splitElement = head;

            while(splitElement.getSplitElement() != null && splitElement != currentElement) {
                if (currentElement.getIndex() > splitElement.getIndex() && currentElement.getIndex() < splitElement.getSplitElement().getIndex()) {
                    break;
                }

                splitElement = splitElement.getSplitElement();
            }

            if (splitElement.getSplitElement() != null && splitElement != currentElement) {
                currentElement.setSplitElement(splitElement.getSplitElement());
            }

            if (splitElement != currentElement) {
                splitElement.setSplitElement(currentElement);
            }

            if (previousElement != null) {
                previousElement.setNextElement(null);
            }

            return currentElement.getValue();
        }
    }

    private ListElement<E> getElement(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            ListElement<E> currentElement = head;
            ListElement<E> splitHead = head;
            int counter = 0;

            while(counter != index && currentElement != null){
                counter++;
                currentElement = currentElement.getNextElement();

                if(currentElement == null && splitHead.getSplitElement() != null) {
                    currentElement = splitHead.getSplitElement();
                    splitHead = currentElement;
                }
            }

            if (currentElement ==null)
                throw new IndexOutOfBoundsException();

            return currentElement;
        }
    }


    @Override
    public E set(int index, E data) {
        ListElement<E> actElem= getElement(index);
        actElem.setValue(data);
        return actElem.getValue();
    }

    @Override
    public int indexOf(E data) {
        int pos=0;
        ListElement<E> actElem = head;
        while(actElem!=null)
        {
            if(actElem.getValue().equals(data)) {
                get(pos);
                return pos;
            }
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

        ListElement<E> actElem = getElement(index-1);

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

        ListElement<E> actElem= head ;
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
        ListElement<E> splitElement = head;

        do {
            while(currentElement != null) {
                currentElement = currentElement.getNextElement();
                counter++;
            }

            if (splitElement != null) {
                splitElement = splitElement.getSplitElement();
                currentElement = splitElement;
            }
        } while(splitElement != null);

        return counter;
    }

    private int maxSplitIndex() {
        int index = 0;
        ListElement<E> previousSplitElement = null;
        ListElement<E> currentElement = head;

        while(currentElement != null) {
            index = currentElement.getIndex();
            previousSplitElement = currentElement;
            currentElement = currentElement.getSplitElement();
        }


        currentElement = previousSplitElement;

        while(currentElement.getNextElement() != null) {
            index++;
            currentElement = currentElement.getNextElement();
        }

        return index;
    }

    private void updateSublistIndexes(ListElement<E> head, List<Integer> indexesList) {
        ListElement<E> currentElement = head;
        for (Integer index : indexesList) {
            currentElement.setIndex(index);
            currentElement = currentElement.getNextElement();
        }
    }

    public void reverseList() {
        if (head != null) {
            ListElement<E> previousElement = null;
            ListElement<E> currentElement = head;
            ListElement<E> nextElement = null;
            ListElement<E> previousSplitElement = null;
            ListElement<E> splitElement = head;
            boolean assignedNewHead = false;

            while (splitElement != null) {
                List<Integer> indexesList = new ArrayList<>();
                while (currentElement != null) {
                    indexesList.add(currentElement.getIndex());
                    nextElement = currentElement.getNextElement();
                    currentElement.setNextElement(previousElement);
                    previousElement = currentElement;
                    currentElement = nextElement;
                }

                if (!assignedNewHead) {
                    head = previousElement;
                    assignedNewHead = true;
                }

                updateSublistIndexes(previousElement, indexesList);
                if (previousSplitElement != null) {
                    previousSplitElement.setSplitElement(previousElement);
                }
                previousSplitElement = previousElement;
                previousElement.setSplitElement(splitElement.getSplitElement());
                splitElement.setSplitElement(null);
                splitElement = previousElement.getSplitElement();
                currentElement = splitElement;
                previousElement = null;
            }
        }
    }
}
