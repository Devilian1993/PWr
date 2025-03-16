package org.example.cpuschedulingsimulator.algorithm;

import org.example.cpuschedulingsimulator.model.CPU;
import org.example.cpuschedulingsimulator.model.Process;
import org.example.cpuschedulingsimulator.engine.SimulationClock;

import java.util.ArrayList;

public class FJCSAlgorithm implements SchedulingAlgorithm {
    @Override
    public void schedule(ArrayList<Process> waitingProcesses, CPU cpu, SimulationClock clock) {
        if (!cpu.isBusy()) {
            cpu.setBusy(true);
            Process processToExecute = waitingProcesses.getFirst();
            waitingProcesses.remove(processToExecute);
            cpu.setExecutedProcess(processToExecute);
            processToExecute.setAssignmentTime(clock.getTimeSinceStart());
            clock.contextChange();
        }
    }
}
