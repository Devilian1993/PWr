package org.example.cpuschedulingsimulator.service;

import org.example.cpuschedulingsimulator.algorithm.*;
import org.example.cpuschedulingsimulator.dto.*;
import org.example.cpuschedulingsimulator.engine.SimulationState;
import org.example.cpuschedulingsimulator.model.SimulationConfig;
import org.example.cpuschedulingsimulator.model.Process;
import org.example.cpuschedulingsimulator.engine.SimulationEngine;
import org.example.cpuschedulingsimulator.util.generator.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SimulationService {
    private SimulationConfig simulationConfig;
    private ArrayList<SimulationEngine> simulationEngines;
    private ArrayList<Process> processes;
    private ProcessGenerator generator;

    public SimulationService(SimulationConfigDTO simulationConfigDTO) {

        simulationConfig = new SimulationConfig(
                simulationConfigDTO.getMinimalProcessCompletionTime(),
                simulationConfigDTO.getMaximalProcessCompletionTime(),
                simulationConfigDTO.getNumberOfProcesses(),
                simulationConfigDTO.getTimeUnit(),
                simulationConfigDTO.isProcessesRandomGenerated(),
                simulationConfigDTO.isSendSimulationState(),
                simulationConfigDTO.getRoundRobinTimeQuantum(),
                simulationConfigDTO.getRoundRobinContextChangeTime(),
                simulationConfigDTO.getProcessesCompletionTimes()

        );

        if (simulationConfig.isProcessesRandomGenerated()) {
            generator = new RandomProcessGenerator(
                    simulationConfig.getMinimalProcessCompletionTime(),
                    simulationConfig.getMaximalProcessCompletionTime(),
                    simulationConfig.getNumberOfProcesses()
            );
        } else {
            generator = new FromListProcessGenerator(
                    simulationConfig.getMinimalProcessCompletionTime(),
                    simulationConfig.getMaximalProcessCompletionTime(),
                    simulationConfig.getNumberOfProcesses(),
                    simulationConfig.getProcessesCompletionTime()
            );
        }

        processes = generator.generateProcesses();

        simulationEngines = new ArrayList<>();

        setupSimulationEngines(simulationEngines, processes);

        startSimulation();
    }

    private void setupSimulationEngines(ArrayList<SimulationEngine> engines, ArrayList<Process> processes) {
        int timeUnit = simulationConfig.getTimeUnit();

        engines.add(new SimulationEngine(
                "FJCS",
                new FJCSAlgorithm(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ));

        engines.add(new SimulationEngine(
                "SJF",
                new SJFAlgorithmNonPreemptive(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ));

        engines.add(new SimulationEngine(
                "SJF pre-emptive",
                new SJFAlgorithmPreemptive(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ));

        engines.add(new SimulationEngine(
                "RR",
                new RRAlgorithm(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ));
    }

    private ArrayList<Process> createProcessesListCopy(ArrayList<Process> processes) {
        ArrayList<Process> processesListCopy = new ArrayList<>();

        for (Process process : processes) {
            processesListCopy.add(new Process(
                    process.getId(),
                    process.getTimeToComplete()
            ));
        }

        return processesListCopy;
    }

    private SimulationStateDTO convertSimulationStateToDTO(SimulationState simulationState) {
        ProcessDTO currentProcessDTO = null;
        if (simulationState.getCurrentProcess() != null) {
            currentProcessDTO = convertProcessToDTO(simulationState.getCurrentProcess());
        }

        ArrayList<ProcessDTO> initialProcessesDTO = simulationState.getInitialProcesses().stream().
        map(this::convertProcessToDTO).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ProcessDTO> waitingProcessesDTO = simulationState.getWaitingProcesses().stream().
                map(this::convertProcessToDTO).collect(Collectors.toCollection(ArrayList::new));

        return new SimulationStateDTO(
                simulationState.getName(),
                simulationState.getCurrentTime(),
                simulationState.isRunning(),
                currentProcessDTO,
                initialProcessesDTO,
                waitingProcessesDTO,
                simulationState.getAvgWaitingTime(),
                simulationState.getMaximumWaitingTime()
        );
    }

    private ProcessDTO convertProcessToDTO(Process process) {
        return new ProcessDTO(
                process.getId(),
                process.isCompleted(),
                process.isPaused(),
                process.getTimeToComplete(),
                process.getInitiationTime(),
                process.getWaitingTime(),
                process.getCompletionTime()
        );
    }

    private void startSimulation() {
        while (!isCompleted()) {
            for (SimulationEngine simulationEngine : simulationEngines) {
                if (!simulationEngine.isCompleted()) {
                    simulationEngine.simulationTick();
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private boolean isCompleted() {
        for (SimulationEngine simulationEngine : simulationEngines) {
            if (!simulationEngine.isCompleted()) {
                return false;
            }
        }
        return true;
    }
}
