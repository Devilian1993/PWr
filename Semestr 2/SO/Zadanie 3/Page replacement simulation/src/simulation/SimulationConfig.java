package simulation;

import algorithms.Algorithm;

public class SimulationConfig {
    private final int uniquePageNumber;
    private final int framesNumber;
    private final int totalRequestsCount;
    private final Algorithm algorithm;
    private final boolean writeToFile;

    public SimulationConfig(int uniquePageNumber, int framesNumber, int totalRequestsCount, Algorithm algorithm) {
        this.uniquePageNumber = uniquePageNumber;
        this.framesNumber = framesNumber;
        this.totalRequestsCount = totalRequestsCount;
        this.algorithm = algorithm;
        this.writeToFile = false;
    }

    public SimulationConfig(int uniquePageNumber, int framesNumber, int totalRequestsCount, Algorithm algorithm, boolean writeToFile) {
        this.uniquePageNumber = uniquePageNumber;
        this.framesNumber = framesNumber;
        this.totalRequestsCount = totalRequestsCount;
        this.algorithm = algorithm;
        this.writeToFile = writeToFile;
    }

    public int getUniquePageNumber() {
        return uniquePageNumber;
    }

    public int getFramesNumber() {
        return framesNumber;
    }

    public int getTotalRequestsCount() {
        return totalRequestsCount;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public boolean isWriteToFile() {
        return writeToFile;
    }
}
