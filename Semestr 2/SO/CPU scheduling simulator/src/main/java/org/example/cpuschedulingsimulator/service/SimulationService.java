package org.example.cpuschedulingsimulator.service;

import org.example.cpuschedulingsimulator.controller.WebSocketController;
import org.example.cpuschedulingsimulator.algorithm.*;
import org.example.cpuschedulingsimulator.dto.*;
import org.example.cpuschedulingsimulator.engine.SimulationState;
import org.example.cpuschedulingsimulator.model.SimulationConfig;
import org.example.cpuschedulingsimulator.model.Process;
import org.example.cpuschedulingsimulator.engine.SimulationEngine;
import org.example.cpuschedulingsimulator.util.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SimulationService {
    private final WebSocketController webSocketController;
    private SimulationConfig simulationConfig;
    private ArrayList<SimulationEngine> simulationEngines;
    private ArrayList<Process> processes;
    private ProcessGenerator generator;
    private boolean running;

    @Autowired
    public SimulationService(WebSocketController webSocketController) {
        this.webSocketController = webSocketController;
    }

    public void createSimulation(SimulationConfigDTO simulationConfigDTO) {
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
        ) {{
            setStateUpdateCallback(state -> {
                SimulationStateDTO simulationStateDTO = convertSimulationStateToDTO(state);
                webSocketController.sendUpdate(simulationStateDTO);
            });
        }});

        engines.add(new SimulationEngine(
                "SJF",
                new SRJFAlgorithm(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ) {{
            setStateUpdateCallback(state -> {
                SimulationStateDTO simulationStateDTO = convertSimulationStateToDTO(state);
                webSocketController.sendUpdate(simulationStateDTO);
            });
        }});

        engines.add(new SimulationEngine(
                "SRJF",
                new SJFAlgorithm(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ) {{
            setStateUpdateCallback(state -> {
                SimulationStateDTO simulationStateDTO = convertSimulationStateToDTO(state);
                webSocketController.sendUpdate(simulationStateDTO);
            });
        }});

        engines.add(new SimulationEngine(
                "RR",
                new RRAlgorithm(),
                timeUnit,
                createProcessesListCopy(processes),
                simulationConfig.getRoundRobinTimeQuantum(),
                simulationConfig.getRoundRobinContextChangeTime()
        ) {{
            setStateUpdateCallback(state -> {
                SimulationStateDTO simulationStateDTO = convertSimulationStateToDTO(state);
                webSocketController.sendUpdate(simulationStateDTO);
            });
        }});
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

    public void startSimulation() {
        running = true;

        while (!isCompleted() && running) {
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

    public void stopSimulation() {
        running = false;
    }

    public void setSimulationSpeed(int speed) {
        for (SimulationEngine simulation : simulationEngines) {
            simulation.setTimeUnit(speed);
        }
    }

    public void setTicksPerNewProcess(int ticksPerNewProcess) {
        for (SimulationEngine simulation : simulationEngines) {
            simulation.setTicksPerNewProcess(ticksPerNewProcess);
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
