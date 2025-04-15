package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.DiskRequest;
import com.example.diskschedulingsimulation.model.Disk;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FCFS implements SchedulingAlgorithm {
    @Override
    public void execute(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        if (disk.getCurrentRequest() == null && !waitingRequests.isEmpty()) {
            disk.setCurrentRequest(waitingRequests.getFirst());
            waitingRequests.removeFirst();
        } else if (disk.getCurrentRequest() != null) {
            disk.moveHead();
            if (disk.getHeadLocation() == disk.getCurrentRequest().getLocation()) {
                ArrayList<DiskRequest> requestsOnLocation = waitingRequests.stream().filter(p -> p.getLocation() == disk.getCurrentRequest().getLocation()).collect(Collectors.toCollection(ArrayList::new));
                for (DiskRequest diskRequest : requestsOnLocation) {
                    if (diskRequest.getLocation() == disk.getCurrentRequest().getLocation() && diskRequest != disk.getCurrentRequest()) {
                        diskRequest.setCompleted(true);
                        waitingRequests.remove(diskRequest);
                    }
                }
                disk.getCurrentRequest().setCompleted(true);
                disk.setCurrentRequest(null);
            }
        }
    }
}
