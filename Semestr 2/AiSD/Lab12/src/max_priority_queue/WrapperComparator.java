package max_priority_queue;

import java.util.Comparator;

class WrapperComparator<V, P> implements Comparator<Wrapper<V, P>> {
    Comparator<P> priorityComparator;

    WrapperComparator(Comparator<P> priorityComparator) {
        this.priorityComparator = priorityComparator;
    }

    @Override
    public int compare(Wrapper<V, P> o1, Wrapper<V, P> o2) {
        return priorityComparator.compare(o1.priority, o2.priority);
    }
}
