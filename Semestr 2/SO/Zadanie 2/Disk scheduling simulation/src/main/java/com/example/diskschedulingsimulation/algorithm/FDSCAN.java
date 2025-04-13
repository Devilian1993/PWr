package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FDSCAN extends RealtimeSchedulingAlgorithm {

    public FDSCAN() {
        baseAlgorithm = new FCFS();
    }

    private DiskRequest getShortestFeasibleDeadlineRequest(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        DiskRequest shortestFeasibleDeadlineRequest = null;
        int shortestFeasibleDeadlineTime = Integer.MAX_VALUE;

        for (DiskRequest waitingRequest : getRealtimeRequests(waitingRequests)) {
            if (disk.getHeadRoute(waitingRequest) <= waitingRequest.getDeadline()
                    && waitingRequest.getDeadline() < shortestFeasibleDeadlineTime) {
                shortestFeasibleDeadlineRequest = waitingRequest;
                shortestFeasibleDeadlineTime = waitingRequest.getDeadline();
            }
        }

        return shortestFeasibleDeadlineRequest;
    }

    private boolean isSCANDirectionLeft(Disk disk, DiskRequest diskRequest) {
        return diskRequest.getLocation() < disk.getHeadLocation() && disk.getHeadLocation() == disk.getSize();
    }

    private DiskRequest getRequestOnLocation(int location, ArrayList<DiskRequest> waitingRequests) {
        for (DiskRequest diskRequest : waitingRequests) {
            if (diskRequest.getLocation() == location) {
                return diskRequest;
            }
        }
        return null;
    }

    @Override
    public void execute(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        if (!anyRealtimeRequests(waitingRequests)) {
            baseAlgorithm.execute(waitingRequests, disk);
        }

        DiskRequest shortestFeasibleDeadlineRequest = getShortestFeasibleDeadlineRequest(waitingRequests, disk);
        if (shortestFeasibleDeadlineRequest == null) {
            baseAlgorithm.execute(waitingRequests, disk);
        } else {
            if (isSCANDirectionLeft(disk, shortestFeasibleDeadlineRequest)) {
                disk.moveHeadLeft();
            } else {
                disk.moveHeadRight();
            }

            DiskRequest requestOnLocation = getRequestOnLocation(disk.getHeadLocation(), waitingRequests);
            if (requestOnLocation != null) {
                requestOnLocation.setCompleted(true);
                waitingRequests.remove(requestOnLocation);
            }
        }
    }
}
