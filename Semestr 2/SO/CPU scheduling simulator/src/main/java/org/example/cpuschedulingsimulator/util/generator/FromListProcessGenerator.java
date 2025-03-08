package org.example.cpuschedulingsimulator.util.generator;

import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;

public class FromListProcessGenerator implements ProcessGenerator {
    private int minTime;
    private int maxTime;
    private int numberOfProcesses;
    private ArrayList<Integer> processesCompletionTimesList;

    public FromListProcessGenerator(int minTime, int maxTime, int numberOfProcesses, ArrayList<Integer> processesCompletionTimes) {
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.numberOfProcesses = numberOfProcesses;
        this.processesCompletionTimesList = processesCompletionTimes;
    }

    @Override
    public Process generateProcess(int processId, int initiationTime) {
        int time = processesCompletionTimesList.get(processId);

        return new Process(processId, time);
    }

    @Override
    public ArrayList<Process> generateProcesses() {
        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < numberOfProcesses; i++) {
            processes.add(generateProcess(i, 0));
        }

        return processes;
    }
}
