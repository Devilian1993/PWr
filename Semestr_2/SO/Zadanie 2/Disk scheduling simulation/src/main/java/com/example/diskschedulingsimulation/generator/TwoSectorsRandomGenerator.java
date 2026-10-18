package com.example.diskschedulingsimulation.generator;

import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.Random;

public class TwoSectorsRandomGenerator implements Generator {

    private final int numberOfRequests;
    private final int diskSize;
    private final Random rand;

    private final int lowerSectorUpperBound;
    private final int upperSectorLowerBound;

    public TwoSectorsRandomGenerator(int numberOfRequests, int diskSize) {
        this.numberOfRequests = numberOfRequests;
        this.diskSize = diskSize;
        rand = new Random();

        lowerSectorUpperBound = diskSize/10;
        upperSectorLowerBound = diskSize/10*9;
    }

    private int getRandomDiskSectorLower() {
        return rand.nextInt(lowerSectorUpperBound);
    }

    private int getRandomDiskSectorUpper() {
        return rand.nextInt(diskSize - upperSectorLowerBound) + upperSectorLowerBound;
    }

    private boolean isNextRequestInLowerSector() {
        return rand.nextInt(2) % 2 == 0;
    }

    public DiskRequest generateProcess() {
        if (isNextRequestInLowerSector()) {
            return new DiskRequest(getRandomDiskSectorLower());
        } else {
            return new DiskRequest(getRandomDiskSectorUpper());
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
