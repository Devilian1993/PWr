package strategies;

import models.Process;
import models.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Strategy {
    final List<Processor> processors;
    final int lowerBound;
    final int upperBound;
    final int NUMBER_OF_RANDOMIZES;

    public Strategy(List<Processor> processors, int lowerBound, int upperBound) {
        this.processors = processors;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.NUMBER_OF_RANDOMIZES = 5;
    }

    List<Processor> listWithProcessorExcluded(Processor processor) {
        return processors.stream().filter(p -> p != processor).collect(Collectors.toCollection(ArrayList::new));
    }

    Processor getRandomProcessor(List<Processor> list) {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }

    public abstract void execute(Processor processor, Process process);
}
