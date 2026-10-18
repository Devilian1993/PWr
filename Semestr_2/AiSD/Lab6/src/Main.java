
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import core.AbstractSortingAlgorithm;
import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;
import pivot.*;

public class Main {
    private static final List<Integer> TEST_SIZES = new ArrayList<>(){{
		add(10);
		add(20);
		add(40);
		add(50);
		add(60);
		add(80);
		add(100);
		add(150);
		add(200);
		add(300);
		add(500);
		add(600);
		add(800);
		add(1000);
		add(1250);
		add(1500);
		add(2000);
		add(2500);
		add(3000);
		add(4000);
		add(5000);
		add(10000);
	}};

	private static final List<Generator<MarkedValue<Integer>>> GENERATORS = new ArrayList<>();
	private static final HashMap<Generator<MarkedValue<Integer>>, String> LIST_GENERATORS = new HashMap<Generator<MarkedValue<Integer>>, String>();
	private static final HashMap<LinkedListGenerator<MarkedValue<Integer>>, String> LINKED_GENERATORS = new HashMap<>();
	private static final List<AbstractSortingAlgorithm<MarkedValue<Integer>>> ALGORITHMS = new ArrayList<>();
	private static final int REPETITIONS = 20;

	private static String generateFileName(AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<?> generator) {
		String algorithmName = "";

		if (algorithm instanceof MergeSortTriplePartition<MarkedValue<Integer>>) {
			algorithmName = "merge_sort";
		} else if (algorithm instanceof QuickSortLinkedListOptimised<MarkedValue<Integer>>) {
			algorithmName = "quick_sort";
		}

		String generatorName = "";

		if (generator instanceof OrderedIntegerArrayGenerator) {
			generatorName = "ordered_integer_array_generator";
		} else if (generator instanceof ReversedIntegerArrayGenerator) {
			generatorName = "reversed_integer_array_generator";
		} else if (generator instanceof RandomIntegerArrayGenerator) {
			generatorName = "random_integer_array_generator";
		} else if (generator instanceof ShuffledIntegerArrayGenerator) {
			generatorName = "shuffled_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("ordered")) {
			generatorName = "ordered_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("reversed")) {
			generatorName = "reversed_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("random")) {
			generatorName = "random_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("shuffled")) {
			generatorName = "shuffled_integer_array_generator";
		}

		String pivotName = "";

		if (algorithm instanceof QuickSortLinkedListOptimised<?> && ((QuickSortLinkedListOptimised<MarkedValue<Integer>>) algorithm).getPivotSelector() instanceof RandomPivotSelector<MarkedValue<Integer>>) {
			pivotName = "_random_pivot";
		} else if (algorithm instanceof QuickSortLinkedListOptimised<?> && ((QuickSortLinkedListOptimised<MarkedValue<Integer>>) algorithm).getPivotSelector() instanceof FirstElementPivotSelector<MarkedValue<Integer>>) {
			pivotName = "_first_element_pivot";
		}

		return algorithmName + "_" + generatorName + pivotName + "_result.txt";
	}

	private static String generateFileNameLinked(AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm, LinkedListGenerator<?> generator) {
		String algorithmName = "";

		if (algorithm instanceof MergeSortTriplePartition<MarkedValue<Integer>>) {
			algorithmName = "merge_sort";
		} else if (algorithm instanceof QuickSortLinkedListOptimised<MarkedValue<Integer>>) {
			algorithmName = "quick_sort";
		}

		String generatorName = "";

		if (LINKED_GENERATORS.get(generator).equals("ordered")) {
			generatorName = "ordered_integer_list_generator";
		} else if (LINKED_GENERATORS.get(generator).equals("reversed")) {
			generatorName = "reversed_integer_array_generator";
		} else if (LINKED_GENERATORS.get(generator).equals("random")) {
			generatorName = "random_integer_array_generator";
		} else if (LINKED_GENERATORS.get(generator).equals("shuffled")) {
			generatorName = "shuffled_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("ordered")) {
			generatorName = "ordered_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("reversed")) {
			generatorName = "reversed_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("random")) {
			generatorName = "random_integer_array_generator";
		} else if (LIST_GENERATORS.get(generator).equals("shuffled")) {
			generatorName = "shuffled_integer_array_generator";
		}

		String pivotName = "";

		if (algorithm instanceof QuickSortLinkedListOptimised<?> && ((QuickSortLinkedListOptimised<MarkedValue<Integer>>) algorithm).getPivotSelector() instanceof RandomPivotSelector<MarkedValue<Integer>>) {
			pivotName = "_random_pivot";
		} else if (algorithm instanceof QuickSortLinkedListOptimised<?> && ((QuickSortLinkedListOptimised<MarkedValue<Integer>>) algorithm).getPivotSelector() instanceof FirstElementPivotSelector<MarkedValue<Integer>>) {
			pivotName = "_first_element_pivot";
		}

		return algorithmName + "_" + generatorName + pivotName + "_linked_list_result.txt";
	}

    private static void writeResultMerge(testing.results.Result result, int testSize, AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<MarkedValue<Integer>> generator) throws IOException {
		String fileName = generator instanceof LinkedListGenerator ? generateFileNameLinked(algorithm, (LinkedListGenerator<?>) generator) : generateFileName(algorithm, generator);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(String.format("%d;%f;%f;0;0;%f;%f\n", testSize, result.averageTimeInMilliseconds(), result.timeStandardDeviation(), result.averageComparisons(), result.comparisonsStandardDeviation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	private static void writeResultQuick(testing.results.swapping.Result result, int testSize, AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<MarkedValue<Integer>> generator) throws IOException {
		String fileName = generator instanceof LinkedListGenerator ? generateFileNameLinked(algorithm, (LinkedListGenerator<?>) generator) : generateFileName(algorithm, generator);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
			bufferedWriter.write(String.format("%d;%f;%f;%f;%f;%f;%f\n", testSize, result.averageTimeInMilliseconds(), result.timeStandardDeviation(),result.averageSwaps(), result.swapsStandardDeviation(), result.averageComparisons(), result.comparisonsStandardDeviation()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void printLabel(AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm, int testSize) {
		String sizeInfo = " for size " + testSize;
		if (algorithm instanceof MergeSortTriplePartition<?>) {
			System.out.println("Test MergeSort with triple partition" + sizeInfo);
		} else if (algorithm instanceof QuickSortLinkedListOptimised<?> && ((QuickSortLinkedListOptimised<MarkedValue<Integer>>) algorithm).getPivotSelector() instanceof RandomPivotSelector<MarkedValue<Integer>>) {
			System.out.println("Test QuickSort optimised for linked lists with random pivot selection" + sizeInfo);
		} else if (algorithm instanceof QuickSortLinkedListOptimised<?> && ((QuickSortLinkedListOptimised<MarkedValue<Integer>>) algorithm).getPivotSelector() instanceof FirstElementPivotSelector<MarkedValue<Integer>>) {
			System.out.println("Test QuickSort optimised for linked lists with first element pivot selection" + sizeInfo);
		}

	}

	private static void runTestMerge(AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<MarkedValue<Integer>> generator, int testSize) throws IOException {
		testing.results.Result result = Tester.runNTimes(algorithm, generator, testSize, REPETITIONS);
		printLabel(algorithm, testSize);
		if (algorithm instanceof MergeSortTriplePartition<MarkedValue<Integer>>) {
			printAllStatisticsMerge(result);
		}
		writeResultMerge(result, testSize, algorithm, generator);
	}

	private static void runTestQuick(AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<MarkedValue<Integer>> generator, int testSize) throws IOException {
		testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, testSize, REPETITIONS);
		printLabel(algorithm, testSize);
		if (algorithm instanceof QuickSortLinkedListOptimised<MarkedValue<Integer>>) {
			printAllStatisticsQuick(result);
		}
		writeResultQuick(result, testSize, algorithm, generator);
	}

	private static void runSingleTest(AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<Integer> generator, int testSize) throws IOException {
            testing.results.Result result = Tester.runNTimes(algorithm, new MarkingGenerator<>(generator), testSize, REPETITIONS);
            printLabel(algorithm, testSize);
		if (algorithm instanceof MergeSortTriplePartition<MarkedValue<Integer>>) {
			printAllStatisticsMerge(result);
		}
    }

	private static void runSingleTest(AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<Integer> generator, int testSize) throws IOException {
		testing.results.swapping.Result result = Tester.runNTimes(algorithm, new LinkedListGenerator<>(new MarkingGenerator<>(generator)), testSize, REPETITIONS);
		printLabel(algorithm, testSize);
		if (algorithm instanceof QuickSortLinkedListOptimised<MarkedValue<Integer>>) {
			printAllStatisticsQuick(result);
		}
	}

	private static void setup() {
        Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		GENERATORS.add(new MarkingGenerator<>(new OrderedIntegerArrayGenerator()));
		GENERATORS.add(new MarkingGenerator<>(new ReversedIntegerArrayGenerator()));
		GENERATORS.add(new MarkingGenerator<>(new ShuffledIntegerArrayGenerator()));
		GENERATORS.add(new MarkingGenerator<>(new RandomIntegerArrayGenerator(100, 50)));
		GENERATORS.add(new LinkedListGenerator<>(new MarkingGenerator<>(new OrderedIntegerArrayGenerator())));
		GENERATORS.add(new LinkedListGenerator<>(new MarkingGenerator<>(new ReversedIntegerArrayGenerator())));
		GENERATORS.add(new LinkedListGenerator<>(new MarkingGenerator<>(new ShuffledIntegerArrayGenerator())));
		GENERATORS.add(new LinkedListGenerator<>(new MarkingGenerator<>(new RandomIntegerArrayGenerator(100, 50))));
		LIST_GENERATORS.put(GENERATORS.get(0),"ordered");
		LIST_GENERATORS.put(GENERATORS.get(1),"reversed");
		LIST_GENERATORS.put(GENERATORS.get(2),"shuffled");
		LIST_GENERATORS.put(GENERATORS.get(3),"random");
		LINKED_GENERATORS.put((LinkedListGenerator<MarkedValue<Integer>>) GENERATORS.get(4),"ordered");
		LINKED_GENERATORS.put((LinkedListGenerator<MarkedValue<Integer>>) GENERATORS.get(5),"reversed");
		LINKED_GENERATORS.put((LinkedListGenerator<MarkedValue<Integer>>) GENERATORS.get(6),"shuffled");
		LINKED_GENERATORS.put((LinkedListGenerator<MarkedValue<Integer>>) GENERATORS.get(7),"random");
		//ALGORITHMS.add(new MergeSortTriplePartition<>(markedComparator));
		ALGORITHMS.add(new QuickSortLinkedListOptimised<>(markedComparator, new FirstElementPivotSelector<>()));
		ALGORITHMS.add(new QuickSortLinkedListOptimised<>(markedComparator, new RandomPivotSelector<>()));
	}

	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}

	private static String double2String(double value) {
		return String.format("%.12f", value);
	}

	private static void printAllStatisticsQuick(testing.results.swapping.Result result) {
		printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
		printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

		System.out.println("always sorted: " + result.sorted());
		System.out.println("always stable: " + result.stable());
	}

	private static void printAllStatisticsMerge(testing.results.Result result) {
		printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());

		System.out.println("always sorted: " + result.sorted());
		System.out.println("always stable: " + result.stable());
	}

	private static void runAllTests()  throws IOException {
		for (AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm : ALGORITHMS) {
			for (Generator<MarkedValue<Integer>> generator : GENERATORS) {
				for (Integer testSize : TEST_SIZES) {
                    if (algorithm instanceof MergeSortTriplePartition<MarkedValue<Integer>>) {
                        runTestMerge(algorithm, generator, testSize);
                    } else {
						runTestQuick((AbstractSwappingSortingAlgorithm<MarkedValue<Integer>>) algorithm, generator, testSize);
					}
                }
			}
		}
	}

	public static void main(String[] args) throws IOException{
		setup();
		//runSingleTest(new QuickSortLinkedListOptimised<>(new MarkedValueComparator<Integer>(new IntegerComparator()), new RandomPivotSelector<>()), new ShuffledIntegerArrayGenerator(5), 10000);
		runAllTests();
	}
}
