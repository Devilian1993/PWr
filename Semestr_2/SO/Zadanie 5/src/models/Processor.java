package models;

import simulation.Simulation;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Processor {
    private final int id;
    private final Queue<Process> processQueue;
    private final Queue<Process> waitingProcessQueue;
    private int loadPercentage;

    public Processor(int id) {
        this.id = id;
        this.processQueue = new LinkedList<>();
        this.waitingProcessQueue = new LinkedList<>();
    }

    public Integer getLoadPercentage() {
        return loadPercentage;
    }

    private void calculateLoadPercentage() {
        loadPercentage = processQueue.stream()
                .map(p -> p.requiredPowerPercentage)
                .reduce(0, Integer::sum);
    }

    private Integer getPotentialLoadPercentage(Process process) {
        return processQueue.stream()
                .map(p -> p.requiredPowerPercentage)
                .reduce(0, Integer::sum) + process.requiredPowerPercentage;
    }

    public void enqueueProcess(Process process) {
        if (getPotentialLoadPercentage(process) > 100) {
            waitingProcessQueue.add(process);
            Simulation.incrementOverloads();
        } else {
            processQueue.add(process);
        }
        calculateLoadPercentage();
    }

    public void transferProcessTo(Processor processor) {
        Process process = processQueue.poll();
        processor.enqueueProcess(process);
        processQueue.remove(process);
    }

    public void tick() {
        if (!processQueue.isEmpty()) {
            processQueue.peek().executionTime--;
            if (processQueue.peek().executionTime == 0) {
                processQueue.poll();
            }
        }
        if (!waitingProcessQueue.isEmpty() && getPotentialLoadPercentage(waitingProcessQueue.peek()) <= 100) {
            processQueue.add(waitingProcessQueue.poll());
        }
    }

    public boolean anyProcessesLeft() {
        return !processQueue.isEmpty() || !waitingProcessQueue.isEmpty();
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                '}';
    }
}
