package com.example.diskschedulingsimulation.algorithm;

import java.util.ArrayList;
import com.example.diskschedulingsimulation.model.DiskRequest;
import com.example.diskschedulingsimulation.simulation.Clock;

public interface SchedulingAlgorithm {
    void schedule(ArrayList<DiskRequest> waitingRequests, Clock clock);
}
