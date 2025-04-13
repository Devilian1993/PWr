package com.example.diskschedulingsimulation.algorithm;

import com.example.diskschedulingsimulation.model.DiskRequest;
import com.example.diskschedulingsimulation.model.Disk;

import java.util.ArrayList;

public class FCFS implements SchedulingAlgorithm {
    @Override
    public void execute(ArrayList<DiskRequest> waitingRequests, Disk disk) {
        if (disk.getCurrentRequest() == null && !waitingRequests.isEmpty()) {
            disk.setCurrentRequest(waitingRequests.getFirst());
            waitingRequests.removeFirst();
        } else if (disk.getCurrentRequest() != null) {
            disk.moveHead();
            if (disk.getHeadLocation() == disk.getCurrentRequest().getLocation()) {
                disk.getCurrentRequest().setCompleted(true);
                disk.setCurrentRequest(null);
            }
        }
    }
}
