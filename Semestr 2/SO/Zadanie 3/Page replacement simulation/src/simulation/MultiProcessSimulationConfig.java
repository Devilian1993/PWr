package simulation;

import frame_assignment_algorithms.FrameAlgorithm;

import java.util.List;

public class MultiProcessSimulationConfig {
    private final List<Integer> pagesPerProcess;
    private final int numberOfProcesses;
    private final int numberOfFrames;
    private final int totalRequestsCount;
    private final FrameAlgorithm frameAlgorithm;
    private final int generatorSeed;

    private final int pffTimeWindow;
    private final int pffAddFrameThreshold;
    private final int pffRemoveFrameThreshold;
    private final int wssTimeWindow;

    public MultiProcessSimulationConfig(List<Integer> pagesPerProcess, int numberOfProcesses, int numberOfFrames,
                                        int totalRequestsCount, FrameAlgorithm frameAlgorithm, int generatorSeed,
                                        int pffTimeWindow, int pffAddFrameThreshold, int pffRemoveFrameThreshold,
                                        int wssTimeWindow) {
        this.pagesPerProcess = pagesPerProcess;
        this.numberOfProcesses = numberOfProcesses;
        this.numberOfFrames = numberOfFrames;
        this.totalRequestsCount = totalRequestsCount;
        this.frameAlgorithm = frameAlgorithm;
        this.generatorSeed = generatorSeed;
        this.pffTimeWindow = pffTimeWindow;
        this.pffAddFrameThreshold = pffAddFrameThreshold;
        this.pffRemoveFrameThreshold = pffRemoveFrameThreshold;
        this.wssTimeWindow = wssTimeWindow;
    }

    public List<Integer> getPagesPerProcess() {
        return pagesPerProcess;
    }

    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public int getTotalRequestsCount() {
        return totalRequestsCount;
    }

    public FrameAlgorithm getFrameAlgorithm() {
        return frameAlgorithm;
    }

    public int getGeneratorSeed() {
        return generatorSeed;
    }

    public int getPffTimeWindow() {
        return pffTimeWindow;
    }

    public int getPffAddFrameThreshold() {
        return pffAddFrameThreshold;
    }

    public int getPffRemoveFrameThreshold() {
        return pffRemoveFrameThreshold;
    }

    public int getWssTimeWindow() {
        return wssTimeWindow;
    }
}
