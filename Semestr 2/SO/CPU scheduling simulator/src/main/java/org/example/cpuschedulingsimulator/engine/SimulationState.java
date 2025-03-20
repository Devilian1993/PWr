package org.example.cpuschedulingsimulator.engine;

import org.example.cpuschedulingsimulator.model.CPU;
import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SimulationState {
    private String name;
    private boolean running;

    private CPU cpu;
    private Process currentProcess;
    private ArrayList<Process> initialProcesses;
    private ArrayList<Process> waitingProcesses;

    private double avgWaitingTime;
    private int currentTime;
    private int maximumWaitingTime;
    private int contextChanges;
    private int starvedProcessCount;

    public SimulationState(String name, SimulationClock clock, boolean running, CPU cpu, ArrayList<Process> initialProcesses, ArrayList<Process> waitingProcesses) {
        this.name = name;
        this.running = running;
        this.cpu = cpu;

        this.currentProcess = cpu.getExecutedProcess();
        this.initialProcesses = initialProcesses;
        this.waitingProcesses = waitingProcesses;

        this.currentTime = clock.getTimeSinceStart();
        this.avgWaitingTime = calculateAvgWaitingTime();
        this.maximumWaitingTime = calculateMaximumWaitingTime();
        this.contextChanges = clock.getContextChangeCounter();

        this.starvedProcessCount = calculateNumberOfStarvedProcesses();
    }

    private double calculateAvgWaitingTime() {
        double waitingTimeSum = 0;

        for (Process process : initialProcesses) {
            waitingTimeSum += process.getWaitingTime();
        }

        double avgWaitingTime = waitingTimeSum / initialProcesses.size();
        int starvedProcessThreshold = (int) avgWaitingTime*4;

        initialProcesses.forEach(process -> process.setStarvedThreshold(starvedProcessThreshold));
        return avgWaitingTime;
    }

    private int calculateMaximumWaitingTime() {
        int maximumWaitingTime = 0;

        for (Process process : initialProcesses) {
            if (process.getWaitingTime() > maximumWaitingTime) {
                maximumWaitingTime = process.getWaitingTime();
            }
        }

        return maximumWaitingTime;
    }

    private int calculateNumberOfStarvedProcesses() {
        return initialProcesses.stream().
                filter(Process::isStarved).
                collect(Collectors.toCollection(ArrayList::new)).
                size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
    }

    public ArrayList<Process> getInitialProcesses() {
        return initialProcesses;
    }

    public void setInitialProcesses(ArrayList<Process> initialProcesses) {
        this.initialProcesses = initialProcesses;
    }

    public ArrayList<Process> getWaitingProcesses() {
        return waitingProcesses;
    }

    public void setWaitingProcesses(ArrayList<Process> waitingProcesses) {
        this.waitingProcesses = waitingProcesses;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(float avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public int getMaximumWaitingTime() {
        return maximumWaitingTime;
    }

    public void setMaximumWaitingTime(int maximumWaitingTime) {
        this.maximumWaitingTime = maximumWaitingTime;
    }

    public int getContextChanges() {
        return contextChanges;
    }

    public void setContextChanges(int contextChanges) {
        this.contextChanges = contextChanges;
    }

    public int getStarvedProcessCount() {
        return starvedProcessCount;
    }
}

