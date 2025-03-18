import java.util.ArrayList;
import java.util.Iterator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int n = 100;
        CollartzIterator iterator = new CollartzIterator(n);

        for (Integer value : iterator) {
            //System.out.println(value);
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }



        for (Iterator<Integer> iterator2 = list.iterator() ; iterator2.hasNext();) {
            System.out.println(iterator2.next());
        }

        //SubswapIterator subswapIterator = new SubswapIterator(iterator2, 4);
    }
}