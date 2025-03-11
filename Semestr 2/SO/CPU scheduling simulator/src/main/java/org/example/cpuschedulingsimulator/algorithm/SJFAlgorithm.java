package org.example.cpuschedulingsimulator.algorithm;

import org.example.cpuschedulingsimulator.engine.SimulationClock;
import org.example.cpuschedulingsimulator.model.CPU;
import org.example.cpuschedulingsimulator.model.Process;

import java.util.ArrayList;

public class SJFAlgorithm implements SchedulingAlgorithm {
    @Override
    public void schedule(ArrayList<Process> waitingProcesses, CPU cpu, SimulationClock clock) {
        if (cpu.isBusy()) {
            Process executedProcess = cpu.getExecutedProcess();
            Process processToExecute = null;
            for (Process process : waitingProcesses) {
                if (process.getTimeToComplete() < executedProcess.getTimeToComplete()) {
                    waitingProcesses.addFirst(executedProcess);
                    processToExecute = process;
                }
            }

            if (processToExecute != null) {
                cpu.setExecutedProcess(processToExecute);
                waitingProcesses.remove(processToExecute);
            }

        } else {
            cpu.setBusy(true);

            int minExecutionTime = Integer.MAX_VALUE;
            Process minProcess = null;

            for (Process process : waitingProcesses) {
                if (process.getTimeToComplete() < minExecutionTime) {
                    minProcess = process;
                }
            }

            cpu.setExecutedProcess(minProcess);
            waitingProcesses.remove(minProcess);
        }
    }
}
