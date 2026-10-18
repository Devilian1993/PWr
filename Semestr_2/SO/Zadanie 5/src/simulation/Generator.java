package simulation;

import models.Processor;
import models.Process;

import java.util.*;

public class Generator {
    private final Random random;
    private final SimulationConfig config;

    public Generator(SimulationConfig config) {
        this.random = new Random(config.generatorSeed);
        this.config = config;
    }

    public Queue<Process> generateProcessQueue() {
        Queue<Process> processQueue = new LinkedList<>();

        for (int i = 0; i < config.totalNumberOfProcesses; i++) {
            processQueue.add(new Process(
                    i,
                    random.nextInt(1, config.maxRequiredProcessorPower + 1),
                    random.nextInt(1, config.maxExecutionTime + 1))
            );
        }

        return processQueue;
    }

    public List<Processor> generateProcessorList() {
        List<Processor> processorList = new ArrayList<>();

        for (int i = 0; i < config.numberOfProcessors; i++) {
            processorList.add(new Processor(i));
        }

        return processorList;
    }
}
