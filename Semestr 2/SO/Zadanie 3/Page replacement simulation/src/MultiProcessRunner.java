import frame_assignment_algorithms.*;
import simulation.MultiProcessSimulation;
import simulation.MultiProcessSimulationConfig;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiProcessRunner {

    private static int NUMBER_OF_PROCESSES;
    private static int NUMBER_OF_FRAMES;
    private static int NUMBER_OF_TOTAL_REQUESTS;
    private static List<Integer> PAGES_PER_PROCESS;
    private static int GENERATOR_SEED;
    private static int PFF_TIME_WINDOW;
    private static int PFF_ADD_FRAME_THRESHOLD;
    private static int PFF_REMOVE_FRAME_THRESHOLD;
    private static int WSS_TIME_WINDOW;

    private static void defaultConfiguration() {
        NUMBER_OF_PROCESSES = 5;
        NUMBER_OF_FRAMES = 30;
        NUMBER_OF_TOTAL_REQUESTS = 50000;
        PAGES_PER_PROCESS = new ArrayList<>(){{
            add(15);
            add(3);
            add(8);
            add(10);
            add(10);
        }};
        GENERATOR_SEED = 1;

        if (PAGES_PER_PROCESS.size() != NUMBER_OF_PROCESSES) {
            throw new RuntimeException("Number of pages per process does not match number of processes");
        }

        PFF_TIME_WINDOW = 10;
        PFF_ADD_FRAME_THRESHOLD = 4;
        PFF_REMOVE_FRAME_THRESHOLD = 0;
        WSS_TIME_WINDOW = 10;
    }

    private static void setValues() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe procesów: ");
        NUMBER_OF_PROCESSES = scanner.nextInt();
        System.out.println("Podaj liczbe ramek: ");
        NUMBER_OF_FRAMES = scanner.nextInt();
        System.out.println("Podaj liczbe zadan: ");
        NUMBER_OF_TOTAL_REQUESTS = scanner.nextInt();
    }

    private static void setupAlgorithms(List<FrameAlgorithm> algorithms) {
        //algorithms.add(new EqualAlgorithm());
        //algorithms.add(new ProportionalAlgorithm());
        algorithms.add(new PFFSteeringAlgorithm(PFF_ADD_FRAME_THRESHOLD, PFF_REMOVE_FRAME_THRESHOLD));
        //algorithms.add(new ZonalAlgorithm());
    }

    private static MultiProcessSimulationConfig createConfig(FrameAlgorithm algorithm) {
        return new MultiProcessSimulationConfig(PAGES_PER_PROCESS, NUMBER_OF_PROCESSES, NUMBER_OF_FRAMES,
                NUMBER_OF_TOTAL_REQUESTS, algorithm, GENERATOR_SEED, PFF_TIME_WINDOW, PFF_ADD_FRAME_THRESHOLD, PFF_REMOVE_FRAME_THRESHOLD,
                WSS_TIME_WINDOW);
    }

    private static void setupSimulations(List<MultiProcessSimulation> simulations, List<FrameAlgorithm> algorithms) {
        for (FrameAlgorithm algorithm : algorithms) {
            MultiProcessSimulationConfig config = createConfig(algorithm);
            simulations.add(new MultiProcessSimulation(config));
        }
    }

    public static void main(String[] args) {
        defaultConfiguration();

        System.out.println("Liczba procesów: " + NUMBER_OF_PROCESSES);
        System.out.println("Liczba ramek: " + NUMBER_OF_FRAMES);
        System.out.println("Liczba różnych stron: " + PAGES_PER_PROCESS.stream().reduce(0, Integer::sum));
        System.out.println("Liczba zadan: " + NUMBER_OF_TOTAL_REQUESTS);
        System.out.println("Liczba stron w procesie: " + PAGES_PER_PROCESS);
        System.out.println();

        List<MultiProcessSimulation> simulations = new ArrayList<>();
        List<FrameAlgorithm> algorithms = new ArrayList<>();

        setupAlgorithms(algorithms);
        setupSimulations(simulations, algorithms);

        for (MultiProcessSimulation simulation : simulations) {
            simulation.run();
            simulation.printResults();
        }
    }
}
