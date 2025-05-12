import algorithms.*;
import models.Page;
import simulation.Simulation;
import simulation.SimulationConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int NUMBER_OF_PAGES;
    static int NUMBER_OF_FRAMES;
    static int NUMBER_OF_REQUESTS;

    public static void defaultConfiguration() {
        NUMBER_OF_PAGES = 10;
        NUMBER_OF_FRAMES = 5;
        NUMBER_OF_REQUESTS = 50000;
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
    public static void main(String[] args) {
        boolean input = false;

        if(input) {
            setValues();
        } else {
            defaultConfiguration();
        }

        List<Simulation> simulations = new ArrayList<>();
        List<Algorithm> algorithms = new ArrayList<>(){{
            add(new FIFO());
            add(new OPT());
            add(new LRU());
            add(new LRUAprox());
            add(new RAND());
        }};

        for (Algorithm algorithm : algorithms) {
            SimulationConfig config = new SimulationConfig(NUMBER_OF_PAGES, NUMBER_OF_FRAMES, NUMBER_OF_REQUESTS, algorithm);
            simulations.add(new Simulation(config));
        }

        for (Simulation simulation : simulations) {
            simulation.run();
            simulation.printResults();
        }

        //simulations.getFirst().printRequests();
    }
}