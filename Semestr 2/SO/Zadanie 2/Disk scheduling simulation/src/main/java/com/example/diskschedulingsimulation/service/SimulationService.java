package com.example.diskschedulingsimulation.service;

import com.example.diskschedulingsimulation.generator.Generator;
import com.example.diskschedulingsimulation.generator.RandomGenerator;
import com.example.diskschedulingsimulation.generator.RandomRealtimeGenerator;
import com.example.diskschedulingsimulation.generator.TwoSectorsRandomGenerator;
import com.example.diskschedulingsimulation.model.DiskRequest;
import org.springframework.stereotype.Service;

import com.example.diskschedulingsimulation.simulation.*;
import com.example.diskschedulingsimulation.algorithm.*;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;

@Service
public class SimulationService {
    private static ArrayList<DiskRequest> requestsDeepCopy(ArrayList<DiskRequest> requests) {
        ArrayList<DiskRequest> requestsDeepCopy = new ArrayList<>();
        for (DiskRequest request : requests) {
            if (!request.isRealTime()) {
                requestsDeepCopy.add(new DiskRequest(request.getLocation()));
            } else {
                requestsDeepCopy.add(new DiskRequest(request.getLocation(), request.getDeadline()));
            }
        }

        return requestsDeepCopy;
    }

    public static void main(String[] args) {
        int DISK_SIZE = 1000;
        int NUMBER_OF_REQUESTS = 1000;
        int NEW_REQUEST_FREQUENCY = 10;

        Generator randomGenerator = new RandomGenerator(NUMBER_OF_REQUESTS, DISK_SIZE);
        Generator twoSectorsRandomGenerator = new TwoSectorsRandomGenerator(NUMBER_OF_REQUESTS, DISK_SIZE);
        Generator randomRealtimeGenerator = new RandomRealtimeGenerator(NUMBER_OF_REQUESTS, DISK_SIZE);

        ArrayList<DiskRequest> requests = randomGenerator.generate();

        ArrayList<Simulation> standardSimulations = new ArrayList<>(){{
            add(new Simulation(
                    "FCFS",
                    new FCFS(),
                    requestsDeepCopy(requests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "SSTF",
                    new SSTF(),
                    requestsDeepCopy(requests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "SCAN",
                    new SCAN(),
                    requestsDeepCopy(requests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "CSCAN",
                    new CSCAN(),
                    requestsDeepCopy(requests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));
        }};

        ArrayList<DiskRequest> twoSectorsRequests = twoSectorsRandomGenerator.generate();

        ArrayList<Simulation> twoSectorsSimulation = new ArrayList<>(){{
            add(new Simulation(
                    "FCFS",
                    new FCFS(),
                    requestsDeepCopy(twoSectorsRequests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "SSTF",
                    new SSTF(),
                    requestsDeepCopy(twoSectorsRequests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "SCAN",
                    new SCAN(),
                    requestsDeepCopy(twoSectorsRequests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "CSCAN",
                    new CSCAN(),
                    requestsDeepCopy(twoSectorsRequests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));
        }};

        ArrayList<DiskRequest> realtimeRequests = randomRealtimeGenerator.generate();

        ArrayList<Simulation> realtimeSimulations = new ArrayList<>(){{
            add(new Simulation(
                    "EDF",
                    new EDF(),
                    requestsDeepCopy(realtimeRequests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));

            add(new Simulation(
                    "FDSCAN",
                    new FDSCAN(),
                    requestsDeepCopy(realtimeRequests),
                    NEW_REQUEST_FREQUENCY,
                    DISK_SIZE
            ));
        }};

        System.out.println("######SYMULACJE STANDARDOWE######");

        for(Simulation simulation : standardSimulations) {
            System.out.println(simulation.getResults());
        }

        //System.out.println("######SYMULACJA ŻĄDAŃ W DWÓCH ODDALONYCH SEKTORACH######");
//
        //for(Simulation simulation : twoSectorsSimulation) {
        //    System.out.println(simulation.getResults());
        //}

        System.out.println("######SYMULACJA ŻĄDAŃ CZASU RZECZYWISTEGO (ALGORYTM BAZOWY: SSTF)######");

        for(Simulation simulation : realtimeSimulations) {
            System.out.println(simulation.getResults());
        }
    }
}
