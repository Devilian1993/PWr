package org.example.cpuschedulingsimulator.util.generator;

import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;
import java.util.Random;

public class RandomProcessGenerator implements ProcessGenerator {
    private int minTime;
    private int maxTime;
    private int numberOfProcesses;
    private Random rand;

    public RandomProcessGenerator(int minTime, int maxTime, int numberOfProcesses) {
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.numberOfProcesses = numberOfProcesses;
        rand = new Random();
    }

    private int getRandomProcessCompletionTime() {
        return rand.nextInt(maxTime - minTime + 1) + minTime;
    }

    public Process generateProcess(int processId, int initiationTime) {
        int time = getRandomProcessCompletionTime();

        return new Process(processId, time);
    }


    public ArrayList<org.example.cpuschedulingsimulator.model.Process> generateProcesses() {
        ArrayList<org.example.cpuschedulingsimulator.model.Process> processes = new ArrayList<>();

        for (int i = 0; i < numberOfProcesses; i++) {
            processes.add(generateProcess(i, 0));
        }

        return processes;
    }
}
