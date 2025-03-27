public class Main {
    public static void main(String[] args) {
        OneWaySplittingList<Integer> list = new OneWaySplittingList<>();

        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        list.remove(3);
        System.out.println(list.get(3).getValue());
    }
}