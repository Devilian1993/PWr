package org.example.cpuschedulingsimulator.controller;

import org.example.cpuschedulingsimulator.dto.SimulationConfigDTO;
import org.example.cpuschedulingsimulator.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulations")
public class SimulationController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping
    public ResponseEntity<String> createSimulation(@RequestBody SimulationConfigDTO configDTO) {
        simulationService.createSimulation(configDTO);
        return ResponseEntity.ok("Symulacja utworzona pomyślnie");
    }
}
