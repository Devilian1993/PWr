package org.example.cpuschedulingsimulator.service;

import org.example.cpuschedulingsimulator.algorithm.*;
import org.example.cpuschedulingsimulator.dto.SimulationConfigDTO;
import org.example.cpuschedulingsimulator.model.SimulationConfig;
import org.example.cpuschedulingsimulator.model.Process;
import org.example.cpuschedulingsimulator.engine.SimulationEngine;
import org.example.cpuschedulingsimulator.util.generator.*;

import java.util.ArrayList;

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
}
