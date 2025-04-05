package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;

public class SCAN implements SchedulingAlgorithm{
    private boolean directionLeft = true;

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
        if (directionLeft) {
            disk.moveHeadLeft();
        } else {
            disk.moveHeadRight();
        }


        DiskRequest requestOnLocation = getRequestOnLocation(disk.getHeadLocation(), waitingRequests);
        if (requestOnLocation != null && disk.getHeadLocation() == requestOnLocation.getLocation()) {
            requestOnLocation.setCompleted(true);
            waitingRequests.remove(requestOnLocation);
        }

        if(disk.getHeadLocation() == 0) {
            directionLeft = false;
        } else if (disk.getHeadLocation() == disk.getSize()) {
            directionLeft = true;
        }
    }
}