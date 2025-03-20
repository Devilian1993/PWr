package org.example.cpuschedulingsimulator.engine;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
    private final int TICKS_PER_BURSTS = 200;
    private final int BURST_SIZE = 100;
    private int ticksPerNewProcess;
    private int timeUnit;

    private SimulationState simulationState;
    private Consumer<SimulationState> stateUpdateCallback;
    private boolean stateSent;

    private boolean generateProcessesInBursts;

    public SimulationEngine(String name, SchedulingAlgorithm algorithm, int timeUnit, ArrayList<Process> initialProcesses, int RRTimeQuantum, int RRContextChangeTime, int ticksPerNewProcess, boolean generateProcessesInBursts) {
        this.name = name;
        clock = new SimulationClock(RRTimeQuantum);
        cpu = new CPU();
        this.algorithm = algorithm;
        this.initialProcesses = initialProcesses;
        this.waitingProcesses = new ArrayList<>();
        this.running = true;
        this.timeUnit = timeUnit;
        this.stateSent = false;
        this.ticksPerNewProcess = ticksPerNewProcess;
        this.generateProcessesInBursts = generateProcessesInBursts;

        waitingProcesses.addAll(initialProcesses.subList(0, initialProcesses.size() / FRACTION_OF_PROCESSESS_WAITING_ON_START).
                stream().peek(process -> process.setWaiting(true)).
                collect(Collectors.toCollection(ArrayList::new)));
    }

    public void simulationTick() {
        if (!generateProcessesInBursts) {
            if (clock.getTimeSinceStart() % ticksPerNewProcess == 0) {
                addNewProcess();
            }
        } else {
            if (clock.getTimeSinceStart() % ticksPerNewProcess*4 == 0) {
                addNewProcess();
            } else if ((clock.getTimeSinceStart() + 1 )% TICKS_PER_BURSTS == 0) {
                waitingProcesses.addAll(initialProcesses.stream().
                        filter(Process::isWaiting).
                        collect(Collectors.toCollection(ArrayList::new)).subList(0, BURST_SIZE).
                        stream().peek(process -> process.setWaiting(true)).
                        collect(Collectors.toCollection(ArrayList::new)));
            }
        }

        if (!waitingProcesses.isEmpty()) {
            algorithm.schedule(waitingProcesses, cpu, clock);
        }

        cpu.executeProcess();

        clock.clockTick();
    }

    public SimulationState getSimulationState() {
        return simulationState;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getTicksPerNewProcess() {
        return ticksPerNewProcess;
    }

    public void setTicksPerNewProcess(int ticksPerNewProcess) {
        this.ticksPerNewProcess = ticksPerNewProcess;
    }

    public void setStateUpdateCallback(Consumer<SimulationState> stateUpdateCallback) {
        this.stateUpdateCallback = stateUpdateCallback;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Process> getInitialProcesses() {
        return initialProcesses;
    }

    public boolean isCompleted() {
        if (waitingProcesses.isEmpty()) {
            for (Process process : initialProcesses) {
                if (!process.isCompleted()) {
                    return false;
                }
            }
            for (Process process : initialProcesses) {
                process.calculateWaitingTime();
            }

            if (!stateSent) {
                updateSimulationState();
                stateSent = true;
            }
            return true;
        } else {
            return false;
        }
    }

    public void addNewProcess() {
        for (Process process : initialProcesses) {
            if (!process.isWaiting() && !process.isCompleted()) {
                waitingProcesses.add(process);
                process.setWaiting(true);
                process.setInitiationTime(clock.getTimeSinceStart());
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
                clock,
                running,
                cpu,
                initialProcesses,
                waitingProcesses
        );

        if (stateUpdateCallback != null) {
            stateUpdateCallback.accept(simulationState);
        }
    }
}
