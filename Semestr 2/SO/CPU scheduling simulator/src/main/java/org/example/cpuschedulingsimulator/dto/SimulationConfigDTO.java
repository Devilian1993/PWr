package org.example.cpuschedulingsimulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class SimulationConfigDTO {
    @JsonProperty("minProcessTime")
    private int minimalProcessCompletionTime;
    @JsonProperty("maxProcessTime")
    private int maximalProcessCompletionTime;
    private int numberOfProcesses;
    private int timeUnit;
    private boolean processesRandomGenerated;
    @JsonProperty("update")
    private boolean sendSimulationState;
    @JsonProperty("rrTimeQuantum")
    private int roundRobinTimeQuantum;
    private int roundRobinContextChangeTime;

    private String processesCompletionTimes;
    private String mode;

    public int getRoundRobinTimeQuantum() {
        return roundRobinTimeQuantum;
    }

    public void setRoundRobinTimeQuantum(int roundRobinTimeQuantum) {
        this.roundRobinTimeQuantum = roundRobinTimeQuantum;
    }

    public boolean isSendSimulationState() {
        return sendSimulationState;
    }

    public void setSendSimulationState(boolean sendSimulationState) {
        this.sendSimulationState = sendSimulationState;
    }

    public boolean isProcessesRandomGenerated() {
        return processesRandomGenerated;
    }

    public void setProcessesRandomGenerated(boolean processesRandomGenerated) {
        this.processesRandomGenerated = processesRandomGenerated;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public void setNumberOfProcesses(int numberOfProcesses) {
        this.numberOfProcesses = numberOfProcesses;
    }

    public int getMaximalProcessCompletionTime() {
        return maximalProcessCompletionTime;
    }

    public void setMaximalProcessCompletionTime(int maximalProcessCompletionTime) {
        this.maximalProcessCompletionTime = maximalProcessCompletionTime;
    }

    public int getMinimalProcessCompletionTime() {
        return minimalProcessCompletionTime;
    }

    public void setMinimalProcessCompletionTime(int minimalProcessCompletionTime) {
        this.minimalProcessCompletionTime = minimalProcessCompletionTime;
    }

    public String getProcessesCompletionTimes() {
        return processesCompletionTimes;
    }

    public void setProcessesCompletionTimes(String processesCompletionTimes) {
        this.processesCompletionTimes = processesCompletionTimes;
    }

    public int getRoundRobinContextChangeTime() {
        return roundRobinContextChangeTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public SimulationConfigDTO(){
    }

    public SimulationConfigDTO(int minimalProcessCompletionTime, int maximalProcessCompletionTime, int numberOfProcesses, int timeUnit, boolean processesRandomGenerated, boolean sendSimulationState, int roundRobinTimeQuantum, int roundRobinContextChangeTime, String processesCompletionTimes) {
        this.minimalProcessCompletionTime = minimalProcessCompletionTime;
        this.maximalProcessCompletionTime = maximalProcessCompletionTime;
        this.numberOfProcesses = numberOfProcesses;
        this.timeUnit = timeUnit;
        this.processesRandomGenerated = processesRandomGenerated;
        this.sendSimulationState = sendSimulationState;
        this.roundRobinTimeQuantum = roundRobinTimeQuantum;
        this.roundRobinContextChangeTime = roundRobinContextChangeTime;

        if (processesRandomGenerated) {
            this.processesCompletionTimes = processesCompletionTimes;
        }
    }
}
