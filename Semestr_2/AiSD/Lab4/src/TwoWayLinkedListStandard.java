import java.sql.SQLOutput;
import java.util.AbstractList;

public class TwoWayLinkedListStandard<E> extends AbstractList<E> {
    TwoWayListElementStandard<E> sentinel;

    TwoWayLinkedListStandard() {
        sentinel = new TwoWayListElementStandard<>(null);
        sentinel.setNextElement(sentinel);
        sentinel.setPreviousElement(sentinel);
    }

    private TwoWayListElementStandard<E> getElement(int index) {
        if(index<0) throw new IndexOutOfBoundsException();
        TwoWayListElementStandard<E> elem=sentinel.getNextElement();
        int counter=0;
        while(elem!=sentinel && counter<index){
            counter++;
            elem=elem.getNextElement();}
        if(elem==sentinel)
            throw new IndexOutOfBoundsException();
        return elem;
    }

    private TwoWayListElementStandard<E> getElement(E value) {
        TwoWayListElementStandard<E> elem=sentinel.getNextElement();
        while(elem!=sentinel && !value.equals(elem.getValue())){
            elem=elem.getNextElement();}
        if(elem==sentinel)
            return null;
        return elem;
    }

    @Override
    public E get(int index) {
        TwoWayListElementStandard<E> elem=getElement(index);
        return elem.getValue();
    }

    public boolean add(E e) {
        TwoWayListElementStandard<E> newElem=new TwoWayListElementStandard<E>(e);
        sentinel.insertBefore(newElem);
        newElem.setNextElement(sentinel);
        sentinel.setPreviousElement(newElem);

        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return sentinel.getNextElement()==sentinel;
    }

    public void reverse() {
        TwoWayListElementStandard<E> previousElement = sentinel;
        TwoWayListElementStandard<E> currentElement = sentinel.getNextElement();
        TwoWayListElementStandard<E> firstElement = sentinel.getNextElement();
        TwoWayListElementStandard<E> nextElement = null;

        while(currentElement != sentinel) {
            nextElement = currentElement.getNextElement();

            currentElement.setPreviousElement(nextElement);
            currentElement.setNextElement(previousElement);

            previousElement = currentElement;
            currentElement = nextElement;
        }

        sentinel.setNextElement(previousElement);
        sentinel.setPreviousElement(firstElement);
    }
}
