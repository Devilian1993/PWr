import heap.TreeArrayBinaryHeap;

public class Main {
    public static void main(String[] args) {
        int H = 2;
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(H);

        heap.add(10);
        heap.add(9);
        heap.add(7);
        heap.add(1);
        heap.add(2);
        heap.add(8);

        //System.out.println(TreeArrayBinaryHeap.hotPathUnoptimal(heap));
        System.out.println(TreeArrayBinaryHeap.hotPath(heap));
    }
}