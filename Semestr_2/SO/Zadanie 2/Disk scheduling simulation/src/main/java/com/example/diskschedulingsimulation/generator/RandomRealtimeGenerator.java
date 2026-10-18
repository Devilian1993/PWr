package com.example.diskschedulingsimulation.generator;

import com.example.diskschedulingsimulation.generator.Generator;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.Random;

public class RandomRealtimeGenerator implements Generator {

    private final int numberOfRequests;
    private final int diskSize;
    private final Random rand;

    private final int REALTIME_REQUESTS_FREQUENCY = 3;
    private final int minRealTimeDeadline;
    private final int maxRealTimeDeadline;

    public RandomRealtimeGenerator(int numberOfRequests, int diskSize) {
        this.numberOfRequests = numberOfRequests;
        this.diskSize = diskSize;
        rand = new Random();

        minRealTimeDeadline = diskSize/5;
        maxRealTimeDeadline = diskSize;
    }

    public RandomRealtimeGenerator(int numberOfRequests, int diskSize, int min, int max) {
        this.numberOfRequests = numberOfRequests;
        this.diskSize = diskSize;
        rand = new Random();

        minRealTimeDeadline = min;
        maxRealTimeDeadline = max;
    }

    private boolean isNextRequestRealTime() {
        return rand.nextInt(REALTIME_REQUESTS_FREQUENCY) % REALTIME_REQUESTS_FREQUENCY == 0;
    }

    private int getRandomDeadline() {
        return rand.nextInt(maxRealTimeDeadline - minRealTimeDeadline) + minRealTimeDeadline;
    }

    private int getRandomDiskSector() {
        return rand.nextInt(diskSize);
    }

    public DiskRequest generateProcess() {
        int sector = getRandomDiskSector();
        if (isNextRequestRealTime()) {
            return new DiskRequest(sector, getRandomDeadline());
        } else {
            return new DiskRequest(sector);
        }
    }


    public ArrayList<DiskRequest> generate() {
        ArrayList<DiskRequest> requests = new ArrayList<>();

        for (int i = 0; i < numberOfRequests; i++) {
            requests.add(generateProcess());
        }

        return requests;
    }
}