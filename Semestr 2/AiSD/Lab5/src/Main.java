
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import core.AbstractSortingAlgorithm;
import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;
import testing.results.swapping.Result;

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

	private static final List<Generator<Integer>> GENERATORS = new ArrayList<>();
	private static final List<AbstractSwappingSortingAlgorithm<MarkedValue<Integer>>> ALGORITHMS = new ArrayList<>();
	private static final int REPETITIONS = 20;

	private static String generateFileName(AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<Integer> generator) {
		String algorithmName = "";

		if (algorithm instanceof ShakerSort<MarkedValue<Integer>>) {
			algorithmName = "shaker_sort";
		} else if (algorithm instanceof InsertionSortBinarySearch<MarkedValue<Integer>>) {
			algorithmName = "insertion_sort_bin_search";
		} else if (algorithm instanceof SelectionSortMaximum<MarkedValue<Integer>>) {
			algorithmName = "selection_sort_max";
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
		}

		return algorithmName + "_" + generatorName + "_result.txt";
	}

    private static void writeResult(Result result, int testSize, AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<Integer> generator) throws IOException {
		String fileName = generateFileName(algorithm, generator);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(String.format("%d;%f;%f;%f;%f;%f;%f\n", testSize, result.averageTimeInMilliseconds(), result.timeStandardDeviation(),result.averageSwaps(), result.swapsStandardDeviation(), result.averageComparisons(), result.comparisonsStandardDeviation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	private static void printLabel(AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, int testSize) {
		String sizeInfo = " for size " + testSize;
		if (algorithm instanceof ShakerSort) {
			System.out.println("Test ShakerSort" + sizeInfo);
		} else if (algorithm instanceof InsertionSortBinarySearch) {
			System.out.println("Test InsertionSort with binary search" + sizeInfo);
		} else if (algorithm instanceof SelectionSortMaximum) {
			System.out.println("Test SelectionSort with selecting maximum" + sizeInfo);
		}

	}

	private static void runTest(AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<Integer> generator, int testSize) throws IOException {
		Result result = Tester.runNTimes(algorithm, new MarkingGenerator<>(generator), testSize, REPETITIONS);
		printLabel(algorithm, testSize);
		printAllStatistics(result);
		writeResult(result, testSize, algorithm, generator);
	}

	private static void runSingleTest(AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm, Generator<Integer> generator, int testSize) throws IOException {
		Result result = Tester.runNTimes(algorithm, new MarkingGenerator<>(generator), testSize, REPETITIONS);
		printLabel(algorithm, testSize);
		printAllStatistics(result);
	}

	private static void setup() {
        Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		GENERATORS.add(new OrderedIntegerArrayGenerator());
		GENERATORS.add(new ReversedIntegerArrayGenerator());
		GENERATORS.add(new ShuffledIntegerArrayGenerator());
		GENERATORS.add(new RandomIntegerArrayGenerator(100, 50));
		//ALGORITHMS.add(new ShakerSort<MarkedValue<Integer>>(markedComparator));
		ALGORITHMS.add(new InsertionSortBinarySearch<MarkedValue<Integer>>(markedComparator));
		//ALGORITHMS.add(new SelectionSortMaximum<MarkedValue<Integer>>(markedComparator));
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

	private static void runAllTests()  throws IOException {
		for (AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm : ALGORITHMS) {
			for (Generator<Integer> generator : GENERATORS) {
				for (Integer testSize : TEST_SIZES) {
					runTest(algorithm, generator, testSize);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		setup();
		//runSingleTest(new SelectionSortMaximum<>(new MarkedValueComparator<Integer>(new IntegerComparator())), new ShuffledIntegerArrayGenerator(5), 10);
		runAllTests();
	}
}
