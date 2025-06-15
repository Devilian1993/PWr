package strategies;

import models.Process;
import models.Processor;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Third extends Strategy {
    public Third(List<Processor> processors, int lowerBound, int upperBound) {
        super(processors, lowerBound, upperBound);
    }

    @Override
    public void execute(Processor processor, Process process) {
        if (processor.getLoadPercentage() < upperBound) {
            processor.enqueueProcess(process);
            return;
        }

        List<Processor> otherProcessors = listWithProcessorExcluded(processor);

        for (int i = 0; i < NUMBER_OF_RANDOMIZES; i++) {
            Simulation.incrementLoadQueries();
            Processor randomProcessor = getRandomProcessor(otherProcessors);
            if (randomProcessor.getLoadPercentage() < upperBound) {
                Simulation.incrementMigrations();
                randomProcessor.enqueueProcess(process);
                return;
            }
        }

        processor.enqueueProcess(process);

        List<Processor> hungryProcessors = processors.stream().filter(p -> p.getLoadPercentage() < lowerBound).collect(Collectors.toCollection(ArrayList::new));
        for (Processor hungryProcessor : hungryProcessors) {
            Simulation.incrementLoadQueries();
            for (int i = 0; i < NUMBER_OF_RANDOMIZES; i++) {
                Processor randomProcessor = getRandomProcessor(otherProcessors);
                if (randomProcessor.getLoadPercentage() > upperBound && randomProcessor.anyProcessesLeft()) {
                    Simulation.incrementMigrations();
                    randomProcessor.transferProcessTo(hungryProcessor);
                    break;
                }
            }
        }
    }
}