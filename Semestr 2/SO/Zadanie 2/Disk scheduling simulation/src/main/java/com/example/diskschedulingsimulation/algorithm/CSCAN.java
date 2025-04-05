package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;

public class CSCAN implements SchedulingAlgorithm{
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
        disk.moveHeadRight();
        System.out.println("Pozycja głowicy: " + disk.getHeadLocation());

        DiskRequest requestOnLocation = getRequestOnLocation(disk.getHeadLocation(), waitingRequests);
        if (requestOnLocation != null && disk.getHeadLocation() == requestOnLocation.getLocation()) {
            waitingRequests.remove(requestOnLocation);
            requestOnLocation.setCompleted(true);
            System.out.println("Żądanie");
        }

        if (disk.getHeadLocation() == disk.getSize()) {
            disk.setHeadLocation(0);
        }
    }
}
