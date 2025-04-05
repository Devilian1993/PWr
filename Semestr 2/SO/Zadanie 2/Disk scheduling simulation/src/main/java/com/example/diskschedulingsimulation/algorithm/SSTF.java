package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;

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
                disk.getCurrentRequest().setCompleted(true);
                disk.setCurrentRequest(null);
            }
        }
    }
}
