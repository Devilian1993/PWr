package org.example.cpuschedulingsimulator.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

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
    private int ticksPerNewProcess = 5;
    private int timeUnit;

    private SimulationState simulationState;
    private Consumer<SimulationState> stateUpdateCallback;

    public SimulationEngine(String name, SchedulingAlgorithm algorithm, int timeUnit, ArrayList<Process> initialProcesses, int RRTimeQuantum, int RRContextChangeTime) {
        this.name = name;
        clock = new SimulationClock(RRTimeQuantum, RRContextChangeTime);
        cpu = new CPU();
        this.algorithm = algorithm;
        this.initialProcesses = initialProcesses;
        this.waitingProcesses = new ArrayList<>();
        this.running = true;
        this.timeUnit = timeUnit;

        waitingProcesses.addAll(initialProcesses.subList(0, initialProcesses.size() / FRACTION_OF_PROCESSESS_WAITING_ON_START));
    }

    public void simulationTick() {
        //long simulationStartTime = System.nanoTime();
        //System.out.println("Symulacja " + name);

        //long addNewProcessStartTime = System.nanoTime();
        if (clock.getTimeSinceStart() % ticksPerNewProcess == 0) {
            addNewProcess();
        }
        //long addNewProcessEndTime = System.nanoTime();
        //long addNewProcessTime = addNewProcessEndTime - addNewProcessStartTime;

        //long scheduleStartTime = System.nanoTime();
        if (!waitingProcesses.isEmpty()) {
            algorithm.schedule(waitingProcesses, cpu, clock);
        }
        //long scheduleEndTime = System.nanoTime();
        //long scheduleTime = scheduleEndTime - scheduleStartTime;

        //long executeStartTime = System.nanoTime();
        cpu.executeProcess();
        //long executeEndTime = System.nanoTime();
        //long executeTime = executeEndTime - executeStartTime;

        //long clockTickStartTime = System.nanoTime();
        clock.clockTick();
        //long clockTickEndTime = System.nanoTime();
        //long clockTickTime = clockTickEndTime - clockTickStartTime;

        //long updateWaitingTimeStartTime = System.nanoTime();
        //updateWaitingTime();
        //long updateWaitingTimeEndTime = System.nanoTime();
        //long updateWaitingTimeTime = updateWaitingTimeEndTime - updateWaitingTimeStartTime;

        //updateSimulationState();

        //long simulationEndTime = System.nanoTime();
        //long executionTime = simulationEndTime - simulationStartTime;
        //System.out.printf("Czas dodania procesu: %d ns\nCzas algorytmu planowania: %d ns\nCzas wykonania procesu: %d ns\n" +
        //                "Czas ticku zegara: %d ns\nCzas aktualizacji czasu oczekiwania: %d ns\nCzas całkowity: %d ns\n",
        //                addNewProcessTime, scheduleTime, executeTime, clockTickTime, updateWaitingTimeTime, executionTime );
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

            updateSimulationState();
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
                clock.getTimeSinceStart(),
                running,
                cpu,
                initialProcesses,
                waitingProcesses
        );

        //if (stateUpdateCallback != null) {
        //    stateUpdateCallback.accept(simulationState);
        //}
    }
}
