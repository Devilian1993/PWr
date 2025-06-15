package strategies;

import models.Processor;

import java.util.List;

public class Second extends Strategy {
    public Second(List<Processor> processors, int lowerBound, int upperBound) {
        super(processors, lowerBound, upperBound);
    }

    @Override
    public void execute(Processor processor) {

    }
}
