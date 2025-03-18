package org.example.cpuschedulingsimulator.model;

public class Process {
    private int id;
    private boolean completed;
    private boolean paused;
    private boolean waiting;
    private int timeToComplete;
    private int initiationTime;
    private int waitingTime;
    private int completionTime;
    private int assignmentTime;

    public Process(int id, int timeToComplete, int initiationTime) {
        this.id = id;
        completed = false;
        paused = false;
        waiting = false;
        this.timeToComplete = timeToComplete;
        this.initiationTime = initiationTime;
        this.waitingTime = 0;
    }

    public Process(int id, int timeToComplete) {
        this.id = id;
        completed = false;
        paused = false;
        this.timeToComplete = timeToComplete;
        this.waitingTime = 0;
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

    public void calculateWaitingTime() {
        if (waitingTime == 0) {
            waitingTime = assignmentTime - initiationTime;
        }
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

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public int getAssignmentTime() {
        return assignmentTime;
    }

    public void setAssignmentTime(int assignmentTime) {
        this.assignmentTime = assignmentTime;
    }

    public void executeProcess() {
        timeToComplete--;

        if (timeToComplete <= 0) {
            completed = true;
        }
    }

    public void waitingTick() {
        waitingTime++;
    }
}
