package simulation;

import algorithms.Algorithm;
import algorithms.LRU;
import frame_assignment_algorithms.FrameAlgorithm;
import frame_assignment_algorithms.PFFSteeringAlgorithm;
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
    private final HashMap<Process, Simulation> virtualSimulationsMap;

    public MultiProcessSimulation(MultiProcessSimulationConfig config) {
        processList = new ArrayList<>();
        frameSet = new ArrayList<>();
        frameAlgorithm = config.getFrameAlgorithm();
        frameAlgorithm.setFrames(frameSet);
        algorithm = new LRU();
        generator = new Generator(config.getGeneratorSeed());
        globalRequests = new ArrayList<>();
        virtualSimulationsMap = new HashMap<>();

        generator.generateProcesses(processList, config.getNumberOfProcesses(), config.getPagesPerProcess(), config.getPffTimeWindow(), config.getWssTimeWindow());
        generator.generateFrames(frameSet, config.getNumberOfFrames());
        setupProcesses(config.getTotalRequestsCount() / config.getNumberOfProcesses());
    }

    private void setupProcesses(int numberOfRequests) {
        for (Process process : processList) {
            process.generateRequestsList(generator, numberOfRequests);
            virtualSimulationsMap.put(process, new Simulation(process, algorithm));
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

    private void updateWssGlobal(List<Process> processes, int globalTime) {
        processes.forEach(p -> p.calculateWSSInWindow(globalTime));
    }

    private void updatePffGlobal(List<Process> processes, int globalTime) {
        processes.forEach(p -> p.calculateNumberOfPageFaultsInWindow());
    }

    public void run() {
        int globalTime = 0;
        for (Page request : globalRequests) {
            updateWssGlobal(processList, globalTime);
            //if (!(frameAlgorithm instanceof PFFSteeringAlgorithm)) {
            //    frameAlgorithm.assignFrames(processList);
            //} else if (globalTime % request.getProcess().getPffTimeWindow() == 0) {
            //    updatePffGlobal(processList, globalTime);
            //    frameAlgorithm.assignFrames(processList);
            //}
            updatePffGlobal(processList, globalTime);
            frameAlgorithm.assignFrames(processList);
            Simulation virtualSimulation = virtualSimulationsMap.get(request.getProcess());
            virtualSimulation.step(globalTime, request);
            virtualSimulationsMap.values().stream().filter(s -> s != virtualSimulation).forEach(Simulation::emptyStep);
            globalTime++;
        }
    }

    public void printResults() {
        String algName = frameAlgorithm.getClass().getSimpleName();
        int pageFaults = virtualSimulationsMap.values().stream().map(Simulation::getPageFaults).reduce(0, Integer::sum);
        int thrashingCount = virtualSimulationsMap.values().stream().map(Simulation::getThrashingCount).reduce(0, Integer::sum);
        int haltedProcessCount = (int) processList.stream().filter(Process::isHalted).count();

        System.out.printf("Symulacja: %s\n" +
                "Liczba błędów stron: %d\n" +
                "Liczba szamotań: %d\n" +
                "Liczba wstrzymanych procesów: %d\n" +
                "\n"
                , algName, pageFaults, thrashingCount, haltedProcessCount);
    }
}
