package strategies;

import models.Processor;

import java.util.List;

public class Third extends Strategy {
    public Third(List<Processor> processors, int lowerBound, int upperBound) {
        super(processors, lowerBound, upperBound);
    }

    @Override
    public void execute(Processor processor) {

    }
}