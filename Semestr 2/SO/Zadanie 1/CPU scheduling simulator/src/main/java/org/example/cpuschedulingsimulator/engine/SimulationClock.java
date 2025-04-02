package org.example.cpuschedulingsimulator.engine;

public class SimulationClock {
    private int timeSinceStart;
    private int RRTimeQuantum;
    private int RRClock;
    private int contextChangeCounter;

    public SimulationClock(int RRTimeQuantum) {
        this.timeSinceStart = 0;
        this.RRTimeQuantum = RRTimeQuantum;
        this.contextChangeCounter = 0;
    }

    public int getTimeSinceStart() {
        return timeSinceStart;
    }

    public void setTimeSinceStart(int timeSinceStart) {
        this.timeSinceStart = timeSinceStart;
    }

    public int getRRTimeQuantum() {
        return RRTimeQuantum;
    }

    public void setRRTimeQuantum(int RRTimeQuantum) {
        this.RRTimeQuantum = RRTimeQuantum;
    }

    public int getRRClock() {
        return RRClock;
    }

    public void setRRClock(int RRClock) {
        this.RRClock = RRClock;
    }

    public void RRTick() {
        RRClock++;
    }

    public void RRReset() {
        RRClock = 0;
    }

    public int getContextChangeCounter() {
        return contextChangeCounter;
    }

    public void contextChange() {
        contextChangeCounter++;
    }

    public void clockTick() {
        timeSinceStart++;
    }
}
