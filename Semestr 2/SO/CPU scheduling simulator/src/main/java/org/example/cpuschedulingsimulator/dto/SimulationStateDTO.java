package org.example.cpuschedulingsimulator.dto;

import java.util.ArrayList;
import java.util.List;

public class SimulationStateDTO {
    private String name;
    private int currentTime;
    private boolean running;

    private ProcessDTO currentProcess;
    private List<ProcessDTO> initialProcesses;
    private List<ProcessDTO> waitingProcesses;

    private float avgWaitingTime;
    private int maximumWaitingTime;

    public SimulationStateDTO() {
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

    public ProcessDTO getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(ProcessDTO currentProcess) {
        this.currentProcess = currentProcess;
    }

    public List<ProcessDTO> getInitialProcesses() {
        return initialProcesses;
    }

    public void setInitialProcesses(List<ProcessDTO> initialProcesses) {
        this.initialProcesses = initialProcesses;
    }

    public List<ProcessDTO> getWaitingProcesses() {
        return waitingProcesses;
    }

    public void setWaitingProcesses(List<ProcessDTO> waitingProcesses) {
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

    public SimulationStateDTO(String name, int currentTime, boolean running,
                              ProcessDTO currentProcess, List<ProcessDTO> initialProcesses,
                              List<ProcessDTO> waitingProcesses, float avgWaitingTime,
                              int maximumWaitingTime) {
        this.name = name;
        this.currentTime = currentTime;
        this.running = running;
        this.currentProcess = currentProcess;
        this.initialProcesses = initialProcesses;
        this.waitingProcesses = waitingProcesses;
        this.avgWaitingTime = avgWaitingTime;
        this.maximumWaitingTime = maximumWaitingTime;
    }
}