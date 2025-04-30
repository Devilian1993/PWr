import com.sun.source.tree.Tree;
import heap.TreeArrayBinaryHeap;

public class Main {
    public static void main(String[] args) {
        int H = 1;
        TreeArrayBinaryHeap<Integer> heap = new TreeArrayBinaryHeap<>(H);

        heap.add(20);
        heap.add(6);
        heap.add(5);
        heap.add(1);
        heap.add(1);
        heap.add(4);
        heap.add(3);

        System.out.println(TreeArrayBinaryHeap.hotPathUnoptimal(heap));
        System.out.println(TreeArrayBinaryHeap.hotPath(heap));
    }
}