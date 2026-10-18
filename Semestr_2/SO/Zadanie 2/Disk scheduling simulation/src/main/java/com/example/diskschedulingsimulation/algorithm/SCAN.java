package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SCAN implements SchedulingAlgorithm{
    private boolean directionLeft = true;

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