package com.example.diskschedulingsimulation.simulation;

import com.example.diskschedulingsimulation.algorithm.SchedulingAlgorithm;
import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Simulation {
    protected String name;
    protected final Disk disk;
    protected final ArrayList<DiskRequest> initialRequests;
    private final SchedulingAlgorithm algorithm;
    private final int newRequestFrequency;
    private final ArrayList<DiskRequest> waitingRequests;

    private int currentTime;

    public Simulation(String name, SchedulingAlgorithm algorithm, ArrayList<DiskRequest> initialRequests, int newRequestFrequency) {
        this.name = name;
        this.disk = new Disk(183);
        this.algorithm = algorithm;
        this.initialRequests = initialRequests;
        this.newRequestFrequency = newRequestFrequency;
        this.waitingRequests = new ArrayList<>();
        this.currentTime = 0;
    }
    private void addInitialRequests() {
        final int numberOfInitialRequests = initialRequests.size();
        waitingRequests.addAll(initialRequests.subList(0, numberOfInitialRequests).stream().peek(request -> request.setWaiting(true)).
                collect(Collectors.toCollection(ArrayList::new)));
    }

    private void tick() {
        if (currentTime % newRequestFrequency == 0) {
            addNewRequest();
        }
        algorithm.execute(waitingRequests, disk);
        updateWaitingTime();
        currentTime++;
    }

    private void addNewRequest() {
        for (DiskRequest diskRequest : initialRequests) {
            if (!diskRequest.isWaiting()) {
                diskRequest.setWaiting(true);
                waitingRequests.add(diskRequest);
                return;
            }
        }
    }

    private void updateWaitingTime() {
        waitingRequests.forEach(DiskRequest::waitingTick);
    }

    public State getResults() {
        addInitialRequests();
        while (!isCompleted()) {
            tick();
        }
        return new State(this);
    }

    private boolean isCompleted() {
        return initialRequests.stream().allMatch(DiskRequest::isCompleted);
    }
}
