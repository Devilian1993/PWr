package org.example.cpuschedulingsimulator.model;

import java.util.ArrayList;

public class CPU {

    private Process executedProcess;
    private boolean isBusy;

    public Process getExecutedProcess() {
        return executedProcess;
    }

    public void setExecutedProcess(Process executedProcess) {
        this.executedProcess = executedProcess;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
