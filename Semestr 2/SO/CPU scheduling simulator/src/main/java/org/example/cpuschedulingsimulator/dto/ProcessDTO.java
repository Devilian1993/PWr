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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public int getInitiationTime() {
        return initiationTime;
    }

    public void setInitiationTime(int initiationTime) {
        this.initiationTime = initiationTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
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