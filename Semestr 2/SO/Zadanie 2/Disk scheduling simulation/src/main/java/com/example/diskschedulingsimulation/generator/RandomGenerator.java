package com.example.diskschedulingsimulation.generator;

import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator implements Generator {

    private final int numberOfRequests;
    private final int diskSize;
    private final Random rand;

    public RandomGenerator(int numberOfRequests, int diskSize) {
        this.numberOfRequests = numberOfRequests;
        this.diskSize = diskSize;
        rand = new Random();
    }

    private int getRandomDiskSector() {
        return rand.nextInt(diskSize);
    }

    public DiskRequest generateProcess() {
        int sector = getRandomDiskSector();

        return new DiskRequest(sector);
    }


    public ArrayList<DiskRequest> generate() {
        ArrayList<DiskRequest> requests = new ArrayList<>();

        for (int i = 0; i < numberOfRequests; i++) {
            requests.add(generateProcess());
        }

        return requests;
    }
}
