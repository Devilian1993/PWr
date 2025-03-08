package org.example.cpuschedulingsimulator.algorithm;

import org.example.cpuschedulingsimulator.model.*;
import org.example.cpuschedulingsimulator.model.Process;
import org.example.cpuschedulingsimulator.engine.SimulationClock;

import java.util.ArrayList;

public interface SchedulingAlgorithm {
    void schedule(ArrayList<Process> waitingProcesses, CPU cpu, SimulationClock clock);
}
