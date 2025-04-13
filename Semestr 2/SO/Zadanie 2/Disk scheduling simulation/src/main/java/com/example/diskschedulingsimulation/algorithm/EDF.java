package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EDF extends RealtimeSchedulingAlgorithm {

    boolean executingRTRequest = false;

    public EDF() {
        baseAlgorithm = new FCFS();
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
        if (!anyRealtimeRequests(waitingRequests) && !executingRTRequest) {
            baseAlgorithm.execute(waitingRequests, disk);
        } else {
            ArrayList<DiskRequest> deadlineRequests = getRealtimeRequests(waitingRequests);

            if (!executingRTRequest) {
                executingRTRequest = true;
                disk.setCurrentRequest(getShortestDeadlineRequest(deadlineRequests));
                waitingRequests.remove(disk.getCurrentRequest());
            }

            disk.moveHead();

            if (disk.getHeadLocation() == disk.getCurrentRequest().getLocation()) {
                disk.getCurrentRequest().setCompleted(true);
                disk.setCurrentRequest(null);
                executingRTRequest = false;
            }
        }
    }
}
