import algorithms.*;
import models.Page;
import simulation.Simulation;
import simulation.SimulationConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int NUMBER_OF_PAGES;
    static int NUMBER_OF_FRAMES;
    static int NUMBER_OF_REQUESTS;
    static int GENERATOR_SEED;

    public static void defaultConfiguration() {
        NUMBER_OF_PAGES = 20;
        NUMBER_OF_FRAMES = 5;
        NUMBER_OF_REQUESTS = 1000;
    }

    public static void setValues() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe stron: ");
        NUMBER_OF_PAGES = scanner.nextInt();
        System.out.println("Podaj liczbe ramek: ");
        NUMBER_OF_FRAMES = scanner.nextInt();
        System.out.println("Podaj liczbe zadan: ");
        NUMBER_OF_REQUESTS = scanner.nextInt();
    }

    public static void writeResults(Simulation simulation, SimulationConfig simulationConfig) {
        String algName = simulation.getAlgorithm().getClass().getSimpleName();
        String fileName = algName + ".csv";
        File file = new File(fileName);
        boolean exists = file.exists();

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            if (!exists) {
                bufferedWriter.write("alg,page,frame,faults,thrash\n");
            }
            bufferedWriter.write(String.format("%s,%d,%d,%d,%d\n", algName, simulationConfig.getUniquePageNumber(), simulationConfig.getFramesNumber()
            , simulation.getPageFaults(), simulation.getThrashingCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        boolean input = false;
        GENERATOR_SEED = random.nextInt();
        //GENERATOR_SEED = 42;

        if(input) {
            setValues();
        } else {
            defaultConfiguration();
        }

        System.out.println("Liczba stron: " + NUMBER_OF_PAGES);
        System.out.println("Liczba ramek: " + NUMBER_OF_FRAMES);
        System.out.println("Liczba zadan: " + NUMBER_OF_REQUESTS);

        List<Simulation> simulations = new ArrayList<>();
        List<Algorithm> algorithms = new ArrayList<>(){{
            add(new FIFO());
            add(new OPT());
            add(new LRU());
            add(new LRUAprox());
            add(new RAND());
        }};

        List<Integer> frames = new ArrayList<>();
        frames.add(3);
        frames.add(4);
        frames.add(5);
        frames.add(8);
        frames.add(10);
        frames.add(15);

        int pages = 20;

        List<SimulationConfig> configs = new ArrayList<>();

        for (Algorithm algorithm : algorithms) {
            for(Integer frame : frames) {
                configs.add(new SimulationConfig(pages, frame, 50000, algorithm, GENERATOR_SEED));
            }
        }

        boolean b = true;
        for (SimulationConfig config : configs) {
            System.out.printf("Liczba ramek: %d\n", config.getFramesNumber());
            Simulation simulation = new Simulation(config);
            simulation.run();
            simulation.printResults();
            //System.out.println();
            //writeResults(simulation, config);
        }

    }
}