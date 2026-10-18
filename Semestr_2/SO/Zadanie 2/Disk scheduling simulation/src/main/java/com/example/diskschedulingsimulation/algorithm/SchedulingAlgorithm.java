package com.example.diskschedulingsimulation.algorithm;

import java.util.ArrayList;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

public interface SchedulingAlgorithm {
    void execute(ArrayList<DiskRequest> waitingRequests, Disk disk);
}
