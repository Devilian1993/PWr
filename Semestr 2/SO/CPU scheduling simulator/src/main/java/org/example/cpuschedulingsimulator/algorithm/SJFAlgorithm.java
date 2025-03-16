package org.example.cpuschedulingsimulator.algorithm;

import org.example.cpuschedulingsimulator.engine.SimulationClock;
import org.example.cpuschedulingsimulator.model.CPU;
import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;

public class SJFAlgorithm implements SchedulingAlgorithm {
    @Override
    public void schedule(ArrayList<Process> waitingProcesses, CPU cpu, SimulationClock clock) {
        if (!cpu.isBusy()) {
            cpu.setBusy(true);

            int minExecutionTime = Integer.MAX_VALUE;
            Process minProcess = null;

            for (Process process : waitingProcesses) {
                if (process.getTimeToComplete() < minExecutionTime) {
                    minProcess = process;
                    minExecutionTime = process.getTimeToComplete();
                }
            }
            
            if (minProcess != null) {
                cpu.setExecutedProcess(minProcess);
                waitingProcesses.remove(minProcess);
                minProcess.setAssignmentTime(clock.getTimeSinceStart());
                clock.contextChange();
            }
        }
    }
}
