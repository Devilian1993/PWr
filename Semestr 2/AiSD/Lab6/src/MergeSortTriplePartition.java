import core.AbstractSortingAlgorithm;

import java.util.*;

public class MergeSortTriplePartition<T> extends AbstractSortingAlgorithm<T> {

    public MergeSortTriplePartition(Comparator<? super T> comparator) {
        super(comparator);
    }

    private List<T> mergeTwo(List<T> list1, List<T> list2) {
        List<T> merged = list1 instanceof LinkedList<T> ? new LinkedList<>() : new ArrayList<>();

        Iterator<T> iterator1 = list1.iterator();
        Iterator<T> iterator2 = list2.iterator();
        T element1 = null;
        T element2 = null;

        if (iterator1.hasNext()) {
            element1 = iterator1.next();
        }

        if (iterator2.hasNext()) {
            element2 = iterator2.next();
        }

        while (element1 != null && element2 != null) {
            if (compare(element1, element2) <= 0) {
                merged.add(element1);
                if (iterator1.hasNext()) {
                    element1 = iterator1.next();
                } else {
                    element1 = null;
                }
            } else {
                merged.add(element2);
                if (iterator2.hasNext()) {
                    element2 = iterator2.next();
                } else {
                    element2 = null;
                }
            }
        }

        if (element1 != null) {
            merged.add(element1);
        }

        if (element2 != null) {
            merged.add(element2);
        }

        while (iterator1.hasNext()) {
            merged.add(iterator1.next());
        }

        while (iterator2.hasNext()) {
            merged.add(iterator2.next());
        }

        return merged;
    }

    private List<T> mergeThree(List<T> list1, List<T> list2, List<T> list3) {
        List<T> twoMerged = mergeTwo(list1, list2);
        return mergeTwo(twoMerged, list3);
    }

    private List<T> mergeSort(List<T> list, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return list instanceof LinkedList<?> ? new LinkedList<>(){{add(list.get(leftIndex));}} : new ArrayList<>(){{add(list.get(leftIndex));}};
        } else if (leftIndex > rightIndex) {
            return new ArrayList<>();
        }

        int length = rightIndex - leftIndex + 1;
        int leftSplit = leftIndex + length / 3;
        int rightSplit = leftIndex + length / 3 * 2;

        return mergeThree(mergeSort(list, leftIndex, leftSplit), mergeSort(list, leftSplit + 1, rightSplit), mergeSort(list, rightSplit + 1, rightIndex));
    }

    @Override
    public List<T> sort(List<T> list) {
        return mergeSort(list, 0, list.size() - 1);
    }

    public static void main(String[] args) {
        MergeSortTriplePartition<Integer> sort = new MergeSortTriplePartition<>(Comparator.naturalOrder());
        List<Integer> list1 = new ArrayList<>(){{add(1); add(3); add(5); add(6);}};
        List<Integer> list2 = new ArrayList<>(){{add(2); add(8); add(10); add(12);}};
        List<Integer> list3 = new ArrayList<>(){{add(4); add(5); add(7); add(9);}};

        List<Integer> merge = sort.mergeThree(list1, list2, list3);

        System.out.println(merge);
    }
}
