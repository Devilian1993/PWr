package simulation;

import algorithms.Algorithm;
import algorithms.LRU;
import frame_assignment_algorithms.FrameAlgorithm;
import generators.Generator;
import models.Frame;
import models.Page;
import models.Process;

import java.util.*;

public class MultiProcessSimulation {
    private final List<Process> processList;
    private final List<Frame> frameSet;
    private final FrameAlgorithm frameAlgorithm;
    private final Algorithm algorithm;
    private final Generator generator;
    private final List<Page> globalRequests;

    public MultiProcessSimulation(MultiProcessSimulationConfig config) {
        processList = new ArrayList<>();
        frameSet = new ArrayList<>();
        frameAlgorithm = config.getFrameAlgorithm();
        frameAlgorithm.setFrames(frameSet);
        algorithm = new LRU();
        generator = new Generator(config.getGeneratorSeed());
        globalRequests = new ArrayList<>();

        generator.generateProcesses(processList, config.getNumberOfProcesses(), config.getPagesPerProcess(), config.getPffTimeWindow());
        generator.generateFrames(frameSet, config.getNumberOfFrames());
        setupProcesses(config.getTotalRequestsCount() / config.getNumberOfProcesses());
    }

    private void setupProcesses(int numberOfRequests) {
        for (Process process : processList) {
            process.generateRequestsList(generator, numberOfRequests);
        }
        createGlobalRequests();
    }

    private void createGlobalRequests() {
        Queue<Iterator<Page>> queue = new LinkedList<>();

        for (Process process : processList) {
            queue.add(process.getRequestsIterator());
        }

        while (!queue.isEmpty()) {
            Iterator<Page> iterator = queue.poll();
            globalRequests.add(iterator.next());

            if (iterator.hasNext()) {
                queue.offer(iterator);
            }
        }
    }

    public void run() {

    }
}
