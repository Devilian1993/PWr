public class Main {
    public static void main(String[] args) {
        IList<Integer> list = new TwoWayLinkedList<>();

        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list.get(2));
        list.remove(1);
        list.remove(0);
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}