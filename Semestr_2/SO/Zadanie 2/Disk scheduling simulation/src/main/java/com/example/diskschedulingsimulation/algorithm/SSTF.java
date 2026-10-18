package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SSTF implements SchedulingAlgorithm{
    @Override
    public void execute(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        if (disk.getCurrentRequest() == null && !waitingRequests.isEmpty()) {
            DiskRequest minDistanceRequest = waitingRequests.getFirst();
            int minDistance = disk.getHeadRoute(minDistanceRequest);

            for (DiskRequest diskRequest : waitingRequests) {
                if (disk.getHeadRoute(diskRequest) < minDistance) {
                    minDistanceRequest = diskRequest;
                    minDistance = disk.getHeadRoute(diskRequest);
                }
            }

            disk.setCurrentRequest(minDistanceRequest);
            waitingRequests.remove(minDistanceRequest);
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
