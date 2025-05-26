import java.util.ArrayList;
import java.util.List;

public class CountingSort {
    private int n = 10;
    private int k = 4;

    public CountingSort(int n, int k) {
        this.n = n;
        this.k = k;
    }

    public List<Integer> sort(List<Integer> list) {
        int[] count = new int[++k];
        List<Integer> output = new ArrayList<>();

        for (Integer item : list) {
            output.add(0);
        }

        for (int i = 0; i < list.size(); i++) {
            count[list.get(i)]++;
        }

        for (int i = 1; i < k; i++) {
            count[i] += count[i - 1];
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            output.set(count[list.get(i)]--, list.get(i));
            count[list.get(i)]--;
        }

        return output;
    }
}
