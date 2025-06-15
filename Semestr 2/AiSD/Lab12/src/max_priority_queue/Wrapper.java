package max_priority_queue;

class Wrapper<V, P> {
    V value;
    P priority;

    Wrapper(V value, P priority) {
        this.value = value;
        this.priority = priority;
    }
}
