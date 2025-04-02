import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoWayLinkedListStandardTests {

    @Test
    public void test() {
        TwoWayLinkedListStandard<Integer> list = new TwoWayLinkedListStandard<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        list.reverse();


        assertEquals(4, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(1, list.get(3));
        assertEquals(0, list.get(4));
    }
}
