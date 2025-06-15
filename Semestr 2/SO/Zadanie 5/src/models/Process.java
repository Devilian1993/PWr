package models;

public class Process {
    protected final int id;
    protected final int requiredPowerPercentage;
    protected int executionTime;

    public Process(int id, int requiredPowerPercentage, int executionTime) {
        this.id = id;
        this.requiredPowerPercentage = requiredPowerPercentage;
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", requiredPowerPercentage=" + requiredPowerPercentage +
                '}';
    }
}
