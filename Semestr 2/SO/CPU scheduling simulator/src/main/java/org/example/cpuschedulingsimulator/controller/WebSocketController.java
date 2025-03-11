package org.example.cpuschedulingsimulator.controller;

import org.example.cpuschedulingsimulator.dto.SimulationStateDTO;
import org.example.cpuschedulingsimulator.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimulationService simulationService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketController(SimulationService simulationService, SimpMessagingTemplate messagingTemplate) {
        this.simulationService = simulationService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/start")
    public void startSimulation() {
        simulationService.startSimulation();
    }

    @MessageMapping("/pause")
    public void pauseSimulation() {
        simulationService.stopSimulation();
    }

    @MessageMapping("/setSpeed")
    public void setSimulationSpeed(int ticksPerUpdate) {
        simulationService.setSimulationSpeed(ticksPerUpdate);
    }

    @MessageMapping("/setTicksPerNewProcess")
    public void setTicksPerNewProcess(int ticksPerNewProcess) {
        simulationService.setTicksPerNewProcess(ticksPerNewProcess);
    }

    public void sendUpdate(SimulationStateDTO stateDTO) {
        messagingTemplate.convertAndSend("/topic/simulation" , stateDTO);
    }
}

