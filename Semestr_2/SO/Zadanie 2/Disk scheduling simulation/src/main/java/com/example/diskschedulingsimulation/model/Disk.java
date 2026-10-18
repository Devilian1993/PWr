package com.example.diskschedulingsimulation.model;

public class Disk {
    private DiskRequest currentRequest;
    private final int size;
    private int headLocation;

    private int totalHeadRoute;

    public Disk(int size) {
        this.size = size;
        headLocation = size/2;
        currentRequest = null;
    }

    public int getSize() {
        return size;
    }

    public DiskRequest getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(DiskRequest currentRequest) {
        this.currentRequest = currentRequest;
    }

    public int getHeadRoute(DiskRequest currentRequest) {
        return Math.abs(currentRequest.getLocation() - headLocation);
    }

    public int getHeadLocation() {
        return headLocation;
    }

    public void setHeadLocation(int headLocation) {
        this.headLocation = headLocation;
    }

    public int getTotalHeadRoute() {
        return totalHeadRoute;
    }

    public void moveHeadRight() {
        headLocation++;
        totalHeadRoute++;
    }

    public void moveHeadLeft() {
        headLocation--;
        totalHeadRoute++;
    }

    public void moveHead() {
        if (currentRequest.getLocation() < headLocation) {
            headLocation--;
        } else {
            headLocation++;
        }
        totalHeadRoute++;
    }
}
