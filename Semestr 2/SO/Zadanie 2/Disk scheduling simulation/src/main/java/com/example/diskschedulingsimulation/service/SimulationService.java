package com.example.diskschedulingsimulation.service;

import com.example.diskschedulingsimulation.model.DiskRequest;
import org.springframework.stereotype.Service;

import com.example.diskschedulingsimulation.simulation.*;
import com.example.diskschedulingsimulation.algorithm.*;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;

@Service
public class SimulationService {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(
                "CSCAN",
                new CSCAN(),
                new ArrayList<>(){{
                    add(new DiskRequest(98));
                    add(new DiskRequest(183));
                    add(new DiskRequest(37));
                    add(new DiskRequest(122));
                    add(new DiskRequest(14));
                    add(new DiskRequest(124));
                    add(new DiskRequest(65));
                    add(new DiskRequest(67));
                }},
                1
        );

        System.out.println(simulation.getResults());
    }
}
