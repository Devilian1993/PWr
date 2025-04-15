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
    private int maxWaitingTime;
    private int executedBeforeDeadlinePercentage;

    public State(Simulation simulation) {
        this.simulation = simulation;
        this.totalHeadDistance = simulation.disk.getTotalHeadRoute();
        this.avgWaitingTime = calculateAvgWaitingTime();
        this.maxWaitingTime = calculateMaxWaitingTime();
        this.executedBeforeDeadlinePercentage = calculateBeforeDeadlinePercentage();
    }

    private double calculateAvgWaitingTime() {
        return (double) simulation.initialRequests.stream().
                mapToInt(DiskRequest::getWaitingTime).sum() / simulation.initialRequests.size();
    }

    private int calculateMaxWaitingTime() {
        return simulation.initialRequests.stream().mapToInt(DiskRequest::getWaitingTime).max().getAsInt();
    }

    private int calculateBeforeDeadlinePercentage() {
        List<DiskRequest> realtimeRequests =  simulation.initialRequests.stream().
                filter(DiskRequest::isRealTime).collect(Collectors.toCollection(ArrayList::new));

        if (realtimeRequests.isEmpty()) {
            return -1;
        }

        return (int) (((float) realtimeRequests.stream().filter(DiskRequest::wasExecutedBeforeDeadline).
                collect(Collectors.toCollection(ArrayList::new)).size() / (float) realtimeRequests.size()) * 100);
    }

    @Override
    public String toString() {
        String deadlineResult;
        if (executedBeforeDeadlinePercentage == -1) {
            deadlineResult = "brak żądań czasu rzeczywistego";
        } else {
            deadlineResult = executedBeforeDeadlinePercentage + "%";
        }

        // Dostosowujemy długość linii nagłówka do długości nazwy symulacji
        int nameLength = simulation.name.length();
        int headerWidth = Math.max(26, nameLength + 6); // Zapewniamy minimalne i odpowiednie marginesy

        // Tworzymy linię z odpowiednią liczbą znaków #
        String headerLine = "#".repeat(headerWidth);

        // Centrujemy nazwę symulacji
        String centeredName = centerText("Symulacja: " + simulation.name, headerWidth - 2);
        String titleLine = "#" + centeredName + "#";

        return String.format(
                "%s\n" +
                        "%s\n" +
                        "%s\n" +
                        "Całkowita liczba przemieszczeń głowicy: %d\n" +
                        "Średni czas oczekiwania żądania: %.2f\n" +
                        "Maksymalny czas oczekiwania żądania: %d\n"+// Ogranicz do 2 miejsc po przecinku
                        "Procent żądań czasu rzeczywistego obsłużonych przed deadlinem: %s\n",
                headerLine, titleLine, headerLine,
                totalHeadDistance, avgWaitingTime, maxWaitingTime, deadlineResult);
    }

    // Metoda pomocnicza do centrowania tekstu
    private String centerText(String text, int width) {
        int spaces = width - text.length();
        int leftPadding = spaces / 2;
        int rightPadding = spaces - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }

}
