package org.example.cpuschedulingsimulator.dto;

public class ProcessDTO {
    private int id;
    private boolean completed;
    private boolean paused;
    private int timeToComplete;
    private int initiationTime;
    private int waitingTime;
    private int completionTime;

    public ProcessDTO() {
    }

    public ProcessDTO(int id, boolean completed, boolean paused, int timeToComplete, int initiationTime, int waitingTime, int completionTime) {
        this.id = id;
        this.completed = completed;
        this.paused = paused;
        this.timeToComplete = timeToComplete;
        this.initiationTime = initiationTime;
        this.waitingTime = waitingTime;
        this.completionTime = completionTime;
    }
}