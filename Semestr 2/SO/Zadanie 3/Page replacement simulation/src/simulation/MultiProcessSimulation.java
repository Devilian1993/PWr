package simulation;

import algorithms.Algorithm;
import algorithms.LRU;
import frame_assignment_algorithms.FrameAlgorithm;
import generators.Generator;
import models.Frame;
import models.Process;

import java.util.ArrayList;
import java.util.List;

public class MultiProcessSimulation {
    private final List<Process> processList;
    private final List<Frame> frameSet;
    private final FrameAlgorithm frameAlgorithm;
    private final Algorithm algorithm;
    private final Generator generator;

    MultiProcessSimulation(MultiProcessSimulationConfig config) {
        processList = new ArrayList<>();
        frameSet = new ArrayList<>();
        frameAlgorithm = config.getFrameAlgorithm();
        algorithm = new LRU();
        generator = new Generator(config.getGeneratorSeed());

        generator.generateProcesses(processList, config.getNumberOfProcesses(), config.getPagesPerProcess(), config.getPffTimeWindow());
        generator.generateFrames(frameSet, config.getNumberOfFrames());
        setupProcesses(config.getTotalRequestsCount() / config.getNumberOfProcesses());
    }

    private void setupProcesses(int numberOfRequests) {
        for (Process process : processList) {
            process.generateRequestsList(generator, numberOfRequests);
        }
    }
}
