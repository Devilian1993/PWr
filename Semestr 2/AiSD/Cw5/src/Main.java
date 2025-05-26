import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(){{
            add(0);
            add(2);
            add(1);
            add(0);
            add(4);
            add(4);
            add(2);
            add(1);
            add(1);
            add(1);
        }};

        CountingSort sort = new CountingSort(10, 4);
        sort.sort(list);
        System.out.println(list);
    }
}