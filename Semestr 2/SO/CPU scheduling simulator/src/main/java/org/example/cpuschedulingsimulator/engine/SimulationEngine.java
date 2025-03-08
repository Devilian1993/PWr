package org.example.cpuschedulingsimulator.engine;

import java.util.ArrayList;

import org.example.cpuschedulingsimulator.model.*;
import org.example.cpuschedulingsimulator.model.Process;
import org.example.cpuschedulingsimulator.algorithm.*;

public class SimulationEngine {

    private String name;
    private SimulationClock clock;
    private CPU cpu;
    private SchedulingAlgorithm algorithm;
    private ArrayList<Process> initialProcesses;


    public SimulationEngine(String name, SchedulingAlgorithm algorithm, int timeUnit, ArrayList<Process> initialProcesses, int RRTimeQuantum, int RRContextChangeTime) {
        this.name = name;
        clock = new SimulationClock(timeUnit, RRTimeQuantum, RRContextChangeTime);
        cpu = new CPU();
        this.algorithm = algorithm;
        this.initialProcesses = initialProcesses;
    }
}
