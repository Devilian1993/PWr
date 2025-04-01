package org.example.cpuschedulingsimulator.dto;

import java.util.ArrayList;
import java.util.List;

public class SimulationStateDTO {
    private String name;
    private double avgWaitingTime;
    private long maximumWaitingTime;
    private int contextChanges;
    private int numberOfStarvedProcesses;

    public SimulationStateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(float avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public long getMaximumWaitingTime() {
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

    public int getNumberOfStarvedProcesses() {
        return numberOfStarvedProcesses;
    }

    public void setNumberOfStarvedProcesses(int numberOfStarvedProcesses) {
        this.numberOfStarvedProcesses = numberOfStarvedProcesses;
    }

    public SimulationStateDTO(String name, double avgWaitingTime,
                              long maximumWaitingTime, int contextChanges, int numberOfStarvedProcesses) {
        this.name = name;
        this.avgWaitingTime = avgWaitingTime;
        this.maximumWaitingTime = maximumWaitingTime;
        this.contextChanges = contextChanges;
        this.numberOfStarvedProcesses = numberOfStarvedProcesses;
    }
}