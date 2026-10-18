package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class RealtimeSchedulingAlgorithm implements SchedulingAlgorithm {
    SchedulingAlgorithm baseAlgorithm;

    boolean anyRealtimeRequests(ArrayList<DiskRequest> waitingRequests) {
        return waitingRequests.stream().anyMatch(DiskRequest::isRealTime);
    }

    ArrayList<DiskRequest> getRealtimeRequests(ArrayList<DiskRequest> waitingRequests) {
        return waitingRequests.stream().filter(DiskRequest::isRealTime).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public abstract void execute(ArrayList<DiskRequest> waitingRequests, Disk disk);
}
