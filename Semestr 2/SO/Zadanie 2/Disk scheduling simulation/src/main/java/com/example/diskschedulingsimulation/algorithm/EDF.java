package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EDF extends RealtimeSchedulingAlgorithm {

    boolean executingRTRequest = false;

    public EDF() {
        baseAlgorithm = new SSTF();
    }

    private DiskRequest getShortestDeadlineRequest(ArrayList<DiskRequest> deadlineRequests) {
        DiskRequest shortestDeadlineRequest = deadlineRequests.getFirst();
        int shortestDeadlineTime = shortestDeadlineRequest.getDeadline();

        for (DiskRequest waitingRequest : deadlineRequests) {
            if (waitingRequest.getDeadline() < shortestDeadlineTime) {
                shortestDeadlineRequest = waitingRequest;
                shortestDeadlineTime = waitingRequest.getDeadline();
            }
        }

        return shortestDeadlineRequest;
    }

    @Override
    public void execute(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        if (!anyRealtimeRequests(waitingRequests) || disk.getCurrentRequest() != null) {
            baseAlgorithm.execute(waitingRequests, disk);
        } else {
            ArrayList<DiskRequest> deadlineRequests = getRealtimeRequests(waitingRequests);

            disk.setCurrentRequest(getShortestDeadlineRequest(deadlineRequests));
            waitingRequests.remove(disk.getCurrentRequest());

            disk.moveHead();
        }
    }
}
