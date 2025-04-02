package org.example.cpuschedulingsimulator.util.generator;

import org.example.cpuschedulingsimulator.model.Process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FromListProcessGenerator implements ProcessGenerator {
    private final String PATH = "src/main/resources/processes.txt";
    private final File FILE = new File(PATH);
    private int numberOfProcesses;
    private ArrayList<Integer> processesCompletionTimesList;

    public FromListProcessGenerator() {
        processesCompletionTimesList = new ArrayList<>();
        loadProcessesFromFile();
        this.numberOfProcesses = processesCompletionTimesList.size();
    }

    private void loadProcessesFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE))) {
            System.out.println("Loading processes from file...");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processesCompletionTimesList.add(Integer.parseInt(line));
            }
            System.out.println("Processes loaded.");
        } catch (Exception e) {
            System.out.println("File not found: " + PATH);
        }
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
