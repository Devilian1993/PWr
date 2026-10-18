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
            updateIndexesPositive(index-1);

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

    private ListElement<E> getSplit(int index) {
        ListElement<E> splitElement = head;
        while(splitElement != null && splitElement.getSplitElement() != null && splitElement.getSplitElement().getIndex() <= index) {
            splitElement = splitElement.getSplitElement();
        }

        return splitElement;
    }

    @Override
    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            ListElement<E> previousElement = null;
            ListElement<E> currentElement = getSplit(index);
            int counter = 0;

            if (currentElement != null) {
                counter = currentElement.getIndex();
            }

            while(counter != index && currentElement != null){
                previousElement = currentElement;
                currentElement = currentElement.getNextElement();
                counter++;
            }

            if (currentElement ==null)
                throw new IndexOutOfBoundsException();

            ListElement<E> splitElement = getSplit(currentElement.getIndex());

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

                while(splitHead.getSplitElement() != null && splitHead.getSplitElement().getIndex() <= index) {
                    splitHead = splitHead.getSplitElement();
                    currentElement = splitHead;
                    counter = splitHead.getIndex();
                }
            }

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
        ListElement<E> splitElement = head;
        ListElement<E> actElem = head;
        while(actElem!=null)
        {
            if(actElem.getValue().equals(data)) {
                get(pos);
                return pos;
            }
            pos++;
            actElem=actElem.getNextElement();

            if(actElem == null && splitElement.getSplitElement() != null) {
                actElem = splitElement.getSplitElement();
            }

        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private void updateIndexesNegative(int removedIndex) {
        ListElement<E> currentElement = head;
        ListElement<E> splitElement = head;

        while (currentElement != null) {
            if (currentElement.getIndex() > removedIndex) {
                currentElement.setIndex(currentElement.getIndex() - 1);
            }
            currentElement = currentElement.getNextElement();

            if (currentElement == null && splitElement.getSplitElement() != null) {
                currentElement = splitElement.getSplitElement();
                splitElement = currentElement;
            }
        }
    }

    private void updateIndexesPositive(int removedIndex) {
        ListElement<E> currentElement = head;
        ListElement<E> splitElement = head;

        while (currentElement != null) {
            if (currentElement.getIndex() > removedIndex) {
                currentElement.setIndex(currentElement.getIndex() + 1);
            }
            currentElement = currentElement.getNextElement();

            if (currentElement == null && splitElement.getSplitElement() != null) {
                currentElement = splitElement.getSplitElement();
                splitElement = currentElement;
            }
        }
    }

    @Override
    public E remove(int index) {
        if(index<0 || head==null) {
            throw new IndexOutOfBoundsException();
        }

        if(index==0) {
            E retValue=head.getValue();
            if (head.getSplitElement() != null && head.getNextElement() != null) {
                head.getNextElement().setSplitElement(head.getSplitElement());
            } else if (head.getSplitElement() != null) {
                head = head.getSplitElement();
            }
            updateIndexesNegative(index);
            return retValue;
        } else {
            ListElement<E> splitElement = head;
            ListElement<E> currentElement = head;
            ListElement<E> previousElement = null;
            while(splitElement.getSplitElement() != null && index >= splitElement.getSplitElement().getIndex()) {
                previousElement = splitElement;
                splitElement = splitElement.getSplitElement();
                currentElement = splitElement;
            }

            if (splitElement.getIndex() == index && previousElement != null) {
                if (splitElement.getNextElement() != null) {
                    previousElement.setSplitElement(splitElement.getNextElement());
                    splitElement.getNextElement().setSplitElement(splitElement.getSplitElement());
                } else {
                    previousElement.setSplitElement(splitElement.getSplitElement());
                }
            }

            while(currentElement != null && currentElement.getIndex() < index ) {
                previousElement = currentElement;
                currentElement = currentElement.getNextElement();
            }

            if(currentElement != null && previousElement != null) {
                E retValue = currentElement.getValue();
                previousElement.setNextElement(currentElement.getNextElement());
                updateIndexesNegative(index);
                return retValue;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
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
            ListElement<E> nextElement = head.getNextElement();
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
