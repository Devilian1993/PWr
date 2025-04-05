package com.example.diskschedulingsimulation.simulation;

import com.example.diskschedulingsimulation.model.Disk;
import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class State {
    private final Simulation simulation;
    private int totalHeadDistance;
    private double avgWaitingTime;
    private int executedBeforeDeadlinePercentage;

    public State(Simulation simulation) {
        this.simulation = simulation;
        this.totalHeadDistance = simulation.disk.getTotalHeadRoute();
        this.avgWaitingTime = calculateAvgWaitingTime();
        this.executedBeforeDeadlinePercentage = calculateBeforeDeadlinePercentage();
    }

    private double calculateAvgWaitingTime() {
        return (double) simulation.initialRequests.stream().
                mapToInt(DiskRequest::getWaitingTime).sum() / simulation.initialRequests.size();
    }

    private int calculateBeforeDeadlinePercentage() {
        List<DiskRequest> realtimeRequests =  simulation.initialRequests.stream().
                filter(DiskRequest::isRealTime).collect(Collectors.toCollection(ArrayList::new));

        if (realtimeRequests.isEmpty()) {
            return -1;
        }

        return (realtimeRequests.stream().filter(DiskRequest::wasExecutedBeforeDeadline).
                collect(Collectors.toCollection(ArrayList::new)).size() / realtimeRequests.size()) * 100;
    }

    @Override
    public String toString() {
        String deadlineResult;
        if (executedBeforeDeadlinePercentage == -1) {
            deadlineResult = "brak żądań czasu rzeczywistego";
        } else {
            deadlineResult = executedBeforeDeadlinePercentage + "%";
        }
        return String.format(
                "####################\n" +
                "# Symulacja: %s  #\n" +
                        "####################\n" +
                        "Całkowita liczba przemieszczeń głowicy: %d\n" +
                        "Średni czas oczekiwania żądania: %f\n" +
                        "Procent żądań czasu rzeczywistego obsłużonych przed deadlinem: %s\n",
        simulation.name, totalHeadDistance, avgWaitingTime, deadlineResult);
    }
}
