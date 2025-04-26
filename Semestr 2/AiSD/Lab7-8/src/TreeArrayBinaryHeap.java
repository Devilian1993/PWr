public class TreeArrayBinaryHeap<T> implements IHeap<T> {

    private TreeNode<T> head;
    private final int H;
    private int length;

    public TreeArrayBinaryHeap(int H) {
        if (H < 1) {
            throw new IllegalArgumentException();
        }
        this.H = H;
        length = 0;
    }

    @Override
    public void clear() {

    }

    private boolean nextNodeLeft(String binaryIndex) {
        binaryIndex = binaryIndex.substring(0, binaryIndex.length() - 1);
        return binaryIndex.charAt(binaryIndex.length() - 1) == '1';
    }

    @Override
    public void add(T element) {
        if (head == null) {
            head = new TreeNode<>(element);
        } else {
            String binaryIndex = Integer.toBinaryString(length + 1);

            while (binaryIndex.length() > 1) {
                
            }


        }
        length++;
    }

    @Override
    public T maximum() {
        return null;
    }

    public int size() {}
}
