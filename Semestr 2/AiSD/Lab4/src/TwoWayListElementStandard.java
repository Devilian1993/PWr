public class TwoWayListElementStandard<E> {
    private E value;
    private TwoWayListElementStandard<E>  nextElement;
    private TwoWayListElementStandard<E>  previousElement;

    public TwoWayListElementStandard(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TwoWayListElementStandard<E>  getNextElement() {
        return nextElement;
    }

    public void setNextElement(TwoWayListElementStandard<E>  nextElement) {
        this.nextElement = nextElement;
    }

    public TwoWayListElementStandard<E>  getPreviousElement() {
        return previousElement;
    }

    public void setPreviousElement(TwoWayListElementStandard<E>  previousElement) {
        this.previousElement = previousElement;
    }

    public void insertAfter(TwoWayListElementStandard<E> elem){
        elem.setNextElement(this.getNextElement());
        elem.setPreviousElement(this);
        this.getNextElement().setPreviousElement(elem);
        this.setNextElement(elem);
    }

    public void insertBefore(TwoWayListElementStandard<E> elem){
        elem.setNextElement(this);
        elem.setPreviousElement(this.getPreviousElement());
        this.getPreviousElement().setNextElement(elem);
        this.setPreviousElement(elem);
    }

    public void remove(){
        this.getNextElement().setPreviousElement(this.getPreviousElement());
        this.getPreviousElement().setNextElement(this.getNextElement());
    }
}
