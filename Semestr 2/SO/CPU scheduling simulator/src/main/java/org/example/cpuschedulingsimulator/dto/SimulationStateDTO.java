package org.example.cpuschedulingsimulator.dto;

import java.util.ArrayList;
import java.util.List;

public class SimulationStateDTO {
    private String name;
    private double avgWaitingTime;
    private int maximumWaitingTime;
    private int contextChanges;

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

    public SimulationStateDTO(String name, double avgWaitingTime,
                              int maximumWaitingTime, int contextChanges) {
        this.name = name;
        this.avgWaitingTime = avgWaitingTime;
        this.maximumWaitingTime = maximumWaitingTime;
        this.contextChanges = contextChanges;
    }
}