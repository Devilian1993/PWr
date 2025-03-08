package org.example.cpuschedulingsimulator.engine;

import org.example.cpuschedulingsimulator.model.CPU;
import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;

public class SimulationState {
    private String name;
    private int currentTime;
    private boolean running;

    private CPU cpu;
    private Process currentProcess;
    private ArrayList<Process> initialProcesses;
    private ArrayList<Process> waitingProcesses;

    private float avgWaitingTime;
    private int maximumWaitingTime;

    public SimulationState(String name, int currentTime, boolean running, CPU cpu, ArrayList<Process> initialProcesses, ArrayList<Process> waitingProcesses) {
        this.name = name;
        this.currentTime = currentTime;
        this.running = running;
        this.cpu = cpu;

        this.currentProcess = cpu.getExecutedProcess();
        this.initialProcesses = initialProcesses;
        this.waitingProcesses = waitingProcesses;

        this.avgWaitingTime = calculateAvgWaitingTime();
        this.maximumWaitingTime = calculateMaximumWaitingTime();
    }

    private float calculateAvgWaitingTime() {
        float waitingTimeSum = 0;

        for (Process process : waitingProcesses) {
            waitingTimeSum += process.getWaitingTime();
        }

        return waitingTimeSum / waitingProcesses.size();
    }

    private int calculateMaximumWaitingTime() {
        int maximumWaitingTime = 0;

        for (Process process : waitingProcesses) {
            if (process.getWaitingTime() > maximumWaitingTime) {
                maximumWaitingTime = process.getWaitingTime();
            }
        }

        return maximumWaitingTime;
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

    public float getAvgWaitingTime() {
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
}
