package simulation;

import strategies.Strategy;

public class SimulationConfig {
    protected final int lowerBound;
    protected final int upperBound;
    protected final int numberOfProcessors;
    protected final int totalNumberOfProcesses;
    protected final int maxExecutionTime;
    protected final int maxRequiredProcessorPower;
    protected final int generatorSeed;
    protected final int strategy;

    public SimulationConfig(int lowerBound, int upperBound, int numberOfProcessors, int totalNumberOfProcesses,
                            int maxExecutionTime, int maxRequiredProcessorPower, int generatorSeed, int strategy) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.numberOfProcessors = numberOfProcessors;
        this.totalNumberOfProcesses = totalNumberOfProcesses;
        this.maxExecutionTime = maxExecutionTime;
        this.maxRequiredProcessorPower = maxRequiredProcessorPower;
        this.generatorSeed = generatorSeed;
        this.strategy = strategy;
    }
}
