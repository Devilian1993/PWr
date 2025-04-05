package com.example.diskschedulingsimulation.model;

public class DiskRequest {
    private final int location;
    private final boolean isRealTime;
    private Integer deadline;
    private int waitingTime;
    private boolean isCompleted;
    private boolean isWaiting;

    public DiskRequest(int location) {
        this.location = location;
        this.isRealTime = false;
        this.deadline = null;
        this.waitingTime = 0;
        this.isCompleted = false;
        this.isWaiting = false;
    }

    public DiskRequest(int location, int deadline) {
        this.location = location;
        this.isRealTime = true;
        this.deadline = deadline;
        this.waitingTime = 0;
        this.isCompleted = false;
        this.isWaiting = false;
    }

    public int getLocation() {
        return location;
    }

    public boolean isRealTime() {
        return isRealTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void waitingTick() {
        waitingTime++;
    }

    public boolean wasExecutedBeforeDeadline() {
        return deadline != null && waitingTime <= deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public Integer getDeadline() {
        return deadline;
    }
}
