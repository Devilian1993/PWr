package simulation;

import models.Processor;
import models.Process;
import strategies.First;
import strategies.Second;
import strategies.Strategy;
import strategies.Third;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Simulation {
    private static int loadQueries = 0;
    private static int migrations = 0;
    private static int overloads = 0;
    private final List<Processor> processors;
    private final Queue<Process> processes;
    private final Strategy strategy;
    private final Generator generator;

    public Simulation(SimulationConfig config) {
        this.generator = new Generator(config);
        this.processors = generator.generateProcessorList();
        this.processes = generator.generateProcessQueue();
        this.strategy = assignStrategy(config);
    }

    private Strategy assignStrategy(SimulationConfig config) {
        return switch (config.strategy) {
            case 1 -> new First(processors, config.lowerBound, config.upperBound);
            case 2 -> new Second(processors, config.lowerBound, config.upperBound);
            case 3 -> new Third(processors, config.lowerBound, config.upperBound);
            default -> throw new IllegalArgumentException("Invalid strategy");
        };
    }

    private boolean anyProcessesLeft() {
        for (Processor processor : processors) {
            if (processor.anyProcessesLeft()) {
                return true;
            }
        }
        return false;
    }

    public void run() {
        int time = 0;
        int NEW_PROCESS_FREQUENCY = 2;
        double totalLoad = 0;
        List<Double> loadHistory = new ArrayList<>();

        while (!processes.isEmpty() || anyProcessesLeft()) {
            if (time % NEW_PROCESS_FREQUENCY == 0 && !processes.isEmpty()) {
                strategy.execute(getRandomProcessor(), processes.poll());
            }

            double tempTotalLoad = 0;
            for (Processor processor : processors) {
                processor.tick();
                tempTotalLoad += processor.getLoadPercentage();
            }

            totalLoad += tempTotalLoad/processors.size();
            loadHistory.add(tempTotalLoad/processors.size());

            time++;
        }

        double averageLoad = totalLoad / time;
        double variance = 0;

        for (Double load : loadHistory) {
            variance += Math.pow(load - averageLoad, 2);
        }
        double standardDeviation = Math.sqrt(variance / time);

        System.out.println("Symulacja strategii nr " + strategy.getClass().getSimpleName());
        System.out.println("Średnie obciążenie procesora: " + averageLoad);
        System.out.println("Odchylenie standardowe obciążeń: " + standardDeviation);
        System.out.println("Liczba zapytań o obciążenie: " + loadQueries);
        System.out.println("Liczba migracji procesów: " + migrations);
        System.out.println("Liczba przeciążeń procesorów: " + overloads);
        System.out.println();
    }

    private Processor getRandomProcessor() {
        return processors.get((int) (Math.random() * processors.size()));
    }

    public static void incrementLoadQueries() {
        loadQueries++;
    }

    public static void incrementMigrations() {
        migrations++;
    }

    public static void incrementOverloads() {
        overloads++;
    }
}
