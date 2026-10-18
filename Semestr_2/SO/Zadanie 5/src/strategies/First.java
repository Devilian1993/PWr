package strategies;

import models.*;
import models.Process;
import simulation.Simulation;

import java.util.List;

public class First extends Strategy {
    public First(List<Processor> processors, int lowerBound, int upperBound) {
        super(processors, lowerBound, upperBound);
    }

    @Override
    public void execute(Processor processor, Process process) {
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
    }
}
