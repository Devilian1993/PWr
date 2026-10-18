package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FDSCAN extends RealtimeSchedulingAlgorithm {

    private boolean scanning = false;
    private boolean directionLeft = true;
    private boolean setDirection = false;
    private DiskRequest currentRequest;

    public FDSCAN() {
        baseAlgorithm = new SSTF();
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
        return diskRequest.getLocation() < disk.getHeadLocation() || disk.getHeadLocation() == disk.getSize();
    }

    private DiskRequest getRequestOnLocation(int location, ArrayList<DiskRequest> waitingRequests) {
        ArrayList<DiskRequest> requestsOnLocation = waitingRequests.stream().filter(p -> p.getLocation() == location).collect(Collectors.toCollection(ArrayList::new));

        if (!requestsOnLocation.isEmpty()) {
            DiskRequest request = requestsOnLocation.getFirst();

            for (int i = 1; i < requestsOnLocation.size(); i++) {
                requestsOnLocation.get(i).setCompleted(true);
                waitingRequests.remove(requestsOnLocation.get(i));
            }

            return request;
        }

        return null;
    }

    @Override
    public void execute(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        DiskRequest shortestFeasibleDeadlineRequest = null;

        if (currentRequest == null) {
            shortestFeasibleDeadlineRequest = getShortestFeasibleDeadlineRequest(waitingRequests, disk);
            currentRequest = shortestFeasibleDeadlineRequest;
        }

        if (shortestFeasibleDeadlineRequest == null) {
            baseAlgorithm.execute(waitingRequests, disk);
            setDirection = false;
        } else {

            if (!setDirection) {
                setDirection = true;
                directionLeft = isSCANDirectionLeft(disk, shortestFeasibleDeadlineRequest);
            }

            DiskRequest requestOnLocation = getRequestOnLocation(disk.getHeadLocation(), waitingRequests);

            if (requestOnLocation != null) {
                requestOnLocation.setCompleted(true);
                waitingRequests.remove(requestOnLocation);

                if (requestOnLocation.getLocation() == currentRequest.getLocation()) {
                    currentRequest = null;
                    setDirection = false;
                }
            }

            if (directionLeft) {
                disk.moveHeadLeft();
            } else {
                disk.moveHeadRight();
            }

            if (disk.getHeadLocation() == disk.getSize() - 1) {
                directionLeft = true;
            } else if (disk.getHeadLocation() == 0) {
                directionLeft = false;
            }
        }
    }
}
