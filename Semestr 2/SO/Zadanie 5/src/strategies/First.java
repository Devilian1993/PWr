package strategies;

import models.Processor;

import java.util.List;

public class First extends Strategy {
    public First(List<Processor> processors, int lowerBound, int upperBound) {
        super(processors, lowerBound, upperBound);
    }

    @Override
    public void execute(Processor processor) {
        List<Processor> otherProcessors = listWithProcessorExcluded(processor);

        for (int i = 0; i < NUMBER_OF_RANDOMIZES; i++) {
            Processor randomProcessor = getRandomProcessor(otherProcessors);
            if (randomP)
        }
    }
}
