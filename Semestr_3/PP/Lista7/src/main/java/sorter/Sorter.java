package sorter;

import items.*;
import visitor.KitchenVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sorter implements KitchenVisitor {
    
    private final Map<Class<?>, List<KitchenItem>> bins = new HashMap<>();
    
    public void sort(List<KitchenItem> items) {
        for (KitchenItem item : items) {
            item.accept(this);
        }
    }
    
    private void addToBin(KitchenItem item) {
        bins.computeIfAbsent(item.getClass(), _ -> new ArrayList<>()).add(item);
    }
    
    @Override
    public void visit(Fork fork) {
        System.out.println("Sorter: Widelca do pojemnika Fork.");
        addToBin(fork);
    }

    @Override
    public void visit(Spoon spoon) {
        System.out.println("Sorter: Łyżka do pojemnika Spoon.");
        addToBin(spoon);
    }

    @Override
    public void visit(Glass glass) {
        System.out.println("Sorter: Szklanka do pojemnika Glass.");
        addToBin(glass);
    }
    
    @SuppressWarnings("unchecked")
    public <T extends KitchenItem> List<T> getLine(Class<T> type) {
        List<KitchenItem> list = bins.getOrDefault(type, new ArrayList<>());

        return (List<T>) list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sorter [");

        int i = 0;
        for (Map.Entry<Class<?>, List<KitchenItem>> entry : bins.entrySet()) {
            sb.append(entry.getKey().getSimpleName());
            sb.append("=");
            sb.append(entry.getValue().size());

            if (i < bins.size() - 1) {
                sb.append(", ");
            }
            i++;
        }

        sb.append("]");
        return sb.toString();
    }
}