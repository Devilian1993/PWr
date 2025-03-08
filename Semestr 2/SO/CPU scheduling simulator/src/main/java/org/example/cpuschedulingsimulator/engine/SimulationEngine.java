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
    private ArrayList<Process> waitingProcesses;
    private boolean running;

    private final int FRACTION_OF_PROCESSESS_WAITING_ON_START = 5;
    private final int NEW_PROCESS_TICK_TIME = 5;

    private SimulationState simulationState;
    private boolean completed;

    public SimulationEngine(String name, SchedulingAlgorithm algorithm, int timeUnit, ArrayList<Process> initialProcesses, int RRTimeQuantum, int RRContextChangeTime) {
        this.name = name;
        clock = new SimulationClock(timeUnit, RRTimeQuantum, RRContextChangeTime);
        cpu = new CPU();
        this.algorithm = algorithm;
        this.initialProcesses = initialProcesses;
        this.waitingProcesses = new ArrayList<>();
        this.running = true;

        waitingProcesses.addAll(initialProcesses.subList(0, initialProcesses.size()/ FRACTION_OF_PROCESSESS_WAITING_ON_START));
    }

    public void simulationTick() {
        if (clock.getTimeSinceStart() % NEW_PROCESS_TICK_TIME == 0) {
            addNewProcess();
        }

        algorithm.schedule(waitingProcesses, cpu, clock);
        cpu.executeProcess();

        clock.clockTick();
        updateWaitingTime();

        updateSimulationState();
    }

    public SimulationState getSimulationState() {
        return simulationState;
    }

    public boolean isCompleted() {
        for (Process process : initialProcesses) {
            if (!process.isCompleted()) {
                return false;
            }
        }

        return true;
    }

    public void addNewProcess() {
        for (Process process : initialProcesses) {
            if (!waitingProcesses.contains(process)) {
                waitingProcesses.add(process);
                break;
            }
        }
    }

    public void updateWaitingTime() {
        for (Process process : waitingProcesses) {
            process.waitingTick();
        }
    }

    private void updateSimulationState() {
        simulationState = new SimulationState(
                name,
                clock.getTimeSinceStart(),
                running,
                cpu,
                initialProcesses,
                waitingProcesses
        );
    }
}
