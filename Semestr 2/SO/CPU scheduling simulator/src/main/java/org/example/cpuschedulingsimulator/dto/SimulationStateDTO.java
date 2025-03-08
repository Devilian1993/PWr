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