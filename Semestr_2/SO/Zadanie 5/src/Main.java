import simulation.Simulation;
import simulation.SimulationConfig;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<SimulationConfig> configs = new ArrayList<>();

        int lowerBound = 25;
        int upperBound = 50;
        int numberOfProcessors = 40;
        int totalNumberOfProcesses = 100000;
        int maxExecutionTime = 100;
        int maxRequiredPower = 10;
        int generatorSeed = 1;

        System.out.println("Konfiguracja");
        System.out.println("Dolna granica obciążenia: " + lowerBound);
        System.out.println("Górna granica obciążenia: " + upperBound);
        System.out.println("Liczba procesorów: " + numberOfProcessors);
        System.out.println("Liczba procesów: " + totalNumberOfProcesses);
        System.out.println("Maksymalny czas trwania procesu: " + maxExecutionTime);
        System.out.println("Maksymalne wymaganie procesu: " + maxRequiredPower);
        System.out.println();

        for (int i = 1; i <= 3; i++) {
            configs.add(new SimulationConfig(
                    lowerBound,
                    upperBound,
                    numberOfProcessors,
                    totalNumberOfProcesses,
                    maxExecutionTime,
                    maxRequiredPower,
                    generatorSeed,
                    i
            ));
        }

        for (SimulationConfig config : configs) {
            Simulation simulation = new Simulation(config);
            simulation.run();
        }
    }
}