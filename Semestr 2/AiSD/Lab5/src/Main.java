
import java.util.Comparator;

import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;
import testing.results.swapping.Result;

public class Main {
	private static Comparator<MarkedValue<Integer>> markedComparator;
	private static Generator<MarkedValue<Integer>> generator;
	private static final int TEST_SIZE = 10;
	private static final int REPETITIONS = 50;

	private static void shakerSort() {
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> shakerSort = new ShakerSort<MarkedValue<Integer>>(markedComparator);
		Result result = Tester.runNTimes(shakerSort, generator, TEST_SIZE, REPETITIONS);
		printAllStatistics(result);
	}

	private static void insertionSortBinarySearch() {
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> shakerSort = new InsertionSortBinarySearch<MarkedValue<Integer>>(markedComparator);
		Result result = Tester.runNTimes(shakerSort, generator, TEST_SIZE, REPETITIONS);
		printAllStatistics(result);
	}

	private static void setup() {
		markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));
	}

	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}

	private static String double2String(double value) {
		return String.format("%.12f", value);
	}

	private static void printAllStatistics(Result result) {
		printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
		printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

		System.out.println("always sorted: " + result.sorted());
		System.out.println("always stable: " + result.stable());
	}

	public static void main(String[] args) {
		setup();
		//AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);
		//Result result = Tester.runNTimes(algorithm, generator, TEST_SIZE, REPETITIONS);
		//printAllStatistics(result);
		//shakerSort();
		insertionSortBinarySearch();
	}
}
