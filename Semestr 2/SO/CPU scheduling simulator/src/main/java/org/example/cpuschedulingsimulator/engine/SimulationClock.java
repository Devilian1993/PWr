package org.example.cpuschedulingsimulator.engine;

public class SimulationClock {
    private int timeUnit;
    private int timeSinceStart;
    private int RRTimeQuantum;
    private int RRClock;
    private int RRContextChangeTime;

    public SimulationClock(int timeUnit, int RRTimeQuantum, int RRContextChangeTime) {
        this.timeUnit = timeUnit;
        this.timeSinceStart = 0;
        this.RRTimeQuantum = RRTimeQuantum;
        this.RRContextChangeTime = RRContextChangeTime;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
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

    public void RRContextChange() {
        timeSinceStart += RRContextChangeTime;
    }

    public void clockTick() {
        timeSinceStart++;
    }
}
