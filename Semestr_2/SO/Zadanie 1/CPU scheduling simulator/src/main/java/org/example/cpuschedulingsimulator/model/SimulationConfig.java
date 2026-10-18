package org.example.cpuschedulingsimulator.model;

import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SimulationConfig {
    private int minimalProcessCompletionTime;
    private int maximalProcessCompletionTime;
    private int numberOfProcesses;
    private int timeUnit;
    private boolean processesRandomGenerated;
    private boolean sendSimulationState;
    private boolean generateProcessesInBursts;
    private int roundRobinTimeQuantum;
    private int roundRobinContextChangeTime;
    private int ticksPerNewProcess;

    private ArrayList<Integer> processesCompletionTime;


    public int getMinimalProcessCompletionTime() {
        return minimalProcessCompletionTime;
    }

    public void setMinimalProcessCompletionTime(int minimalProcessCompletionTime) {
        this.minimalProcessCompletionTime = minimalProcessCompletionTime;
    }

    public int getMaximalProcessCompletionTime() {
        return maximalProcessCompletionTime;
    }

    public void setMaximalProcessCompletionTime(int maximalProcessCompletionTime) {
        this.maximalProcessCompletionTime = maximalProcessCompletionTime;
    }

    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public void setNumberOfProcesses(int numberOfProcesses) {
        this.numberOfProcesses = numberOfProcesses;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public boolean isProcessesRandomGenerated() {
        return processesRandomGenerated;
    }

    public void setProcessesRandomGenerated(boolean processesRandomGenerated) {
        this.processesRandomGenerated = processesRandomGenerated;
    }

    public boolean isSendSimulationState() {
        return sendSimulationState;
    }

    public void setSendSimulationState(boolean sendSimulationState) {
        this.sendSimulationState = sendSimulationState;
    }

    public int getRoundRobinTimeQuantum() {
        return roundRobinTimeQuantum;
    }

    public void setRoundRobinTimeQuantum(int roundRobinTimeQuantum) {
        this.roundRobinTimeQuantum = roundRobinTimeQuantum;
    }

    public int getRoundRobinContextChangeTime() {
        return roundRobinContextChangeTime;
    }

    public ArrayList<Integer> getProcessesCompletionTime() {
        return processesCompletionTime;
    }

    public void setProcessesCompletionTime(ArrayList<Integer> processesCompletionTime) {
        this.processesCompletionTime = processesCompletionTime;
    }

    public int getTicksPerNewProcess() {
        return ticksPerNewProcess;
    }

    public void setTicksPerNewProcess(int ticksPerNewProcess) {
        this.ticksPerNewProcess = ticksPerNewProcess;
    }

    public boolean isGenerateProcessesInBursts() {
        return generateProcessesInBursts;
    }

    public void setGenerateProcessesInBursts(boolean generateProcessesInBursts) {
        this.generateProcessesInBursts = generateProcessesInBursts;
    }

    public SimulationConfig(int minimalProcessCompletionTime, int maximalProcessCompletionTime, int numberOfProcesses, int timeUnit, boolean processesRandomGenerated, boolean sendSimulationState, int roundRobinTimeQuantum, int roundRobinContextChangeTime, String processesCompletionTimesString, int ticksPerNewProcess, boolean generateProcessesInBursts) {
        this.minimalProcessCompletionTime = minimalProcessCompletionTime;
        this.maximalProcessCompletionTime = maximalProcessCompletionTime;
        this.numberOfProcesses = numberOfProcesses;
        this.timeUnit = timeUnit;
        this.processesRandomGenerated = processesRandomGenerated;
        this.sendSimulationState = sendSimulationState;
        this.roundRobinTimeQuantum = roundRobinTimeQuantum;
        this.roundRobinContextChangeTime = roundRobinContextChangeTime;

        this.processesCompletionTime = parseCompletionTimeString(processesCompletionTimesString);
        this.ticksPerNewProcess = ticksPerNewProcess;
        this.generateProcessesInBursts = generateProcessesInBursts;
    }

    private ArrayList<Integer> parseCompletionTimeString(String completionTimeString) {
        if (completionTimeString == null || completionTimeString.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(completionTimeString.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
