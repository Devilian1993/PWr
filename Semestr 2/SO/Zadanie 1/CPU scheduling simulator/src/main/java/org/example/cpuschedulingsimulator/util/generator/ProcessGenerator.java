package org.example.cpuschedulingsimulator.util.generator;

import java.util.ArrayList;

import org.example.cpuschedulingsimulator.model.Process;

public interface ProcessGenerator {
    Process generateProcess(int id, int completionTime);
    ArrayList<Process> generateProcesses();
}
