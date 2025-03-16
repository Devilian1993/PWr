package org.example.cpuschedulingsimulator.algorithm;

import org.example.cpuschedulingsimulator.engine.SimulationClock;
import org.example.cpuschedulingsimulator.model.CPU;
import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;

public class RRAlgorithm implements SchedulingAlgorithm {
    @Override
    public void schedule(ArrayList<Process> waitingProcesses, CPU cpu, SimulationClock clock) {
        if (!cpu.isBusy()) {
            cpu.setBusy(true);
            Process processToExecute = waitingProcesses.getFirst();
            cpu.setExecutedProcess(processToExecute);
            waitingProcesses.remove(processToExecute);
            processToExecute.setAssignmentTime(clock.getTimeSinceStart());
            clock.contextChange();
            clock.RRReset();
        } else if (clock.getRRClock() == clock.getRRTimeQuantum()) {
            Process processToExecute = waitingProcesses.getFirst();
            Process previouslyExecutedProcess = cpu.getExecutedProcess();

            cpu.setExecutedProcess(processToExecute);
            waitingProcesses.add(previouslyExecutedProcess);
            waitingProcesses.remove(processToExecute);

            clock.contextChange();
            clock.RRReset();
        } else {
            clock.RRTick();
        }
    }
}
