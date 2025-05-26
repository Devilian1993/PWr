package frame_assignment_algorithms;

import models.Frame;
import models.Process;

import java.util.Comparator;
import java.util.List;

public class ZonalAlgorithm extends FrameAlgorithm {
    public ZonalAlgorithm(List<Frame> frames) {
        super(frames);
    }

    public ZonalAlgorithm() {
    }

    private int requiredFramesNumber(List<Process> processList) {
        return processList.stream().filter(p -> !p.isHalted()).mapToInt(Process::getWSS).sum();
    }

    @Override
    public void assignFrames(List<Process> processList) {
        if (processList == null || processList.isEmpty() || framesSize() == 0) {
            throw new IllegalArgumentException();
        }

        while (requiredFramesNumber(processList) > frames.size()) {
            processList.stream().filter(p -> !p.isHalted()).max(Comparator.comparingInt(Process::getWSS)).ifPresent(p -> p.setHalted(true));
        }

        int frameIndex = 0;

        for (Process process : processList) {
            if (process.isHalted()) {
                continue;
            }
            int wss = process.getWSS();
            for (int i = 0; i < wss; i++) {
                Frame frame = frames.get(frameIndex);
                process.addFrame(frame);
                frame.assignProcess(process);
                frameIndex++;
            }
        }
    }
}
