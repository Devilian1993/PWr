package frame_assignment_algorithms;

import models.Frame;
import models.Process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProportionalAlgorithm extends FrameAlgorithm {
    public ProportionalAlgorithm(List<Frame> frames) {
        super(frames);
    }

    public ProportionalAlgorithm() {
    }

    private int getFramesForProcess(Process process, int totalPageNumber) {
        return process.getPageSetSize()  * frames.size() / totalPageNumber;
    }

    private double getFramesForProcessRemainder(Process process, int totalPageNumber) {
        return ((double) process.getPageSetSize() / totalPageNumber) * frames.size();
    }

    @Override
    public void assignFrames(List<Process> processList) {
        if (!hasAssignedFrames) {
            if (processList == null || processList.isEmpty() || framesSize() == 0) {
                throw new IllegalArgumentException();
            }

            int totalPageNumber = processList.stream().mapToInt(Process::getPageSetSize).sum();
            int frameIndex = 0;

            for (Process process : processList) {
                int framesForProcess = getFramesForProcess(process, totalPageNumber);
                for (int i = 0; i < framesForProcess; i++) {
                    Frame frame = frames.get(frameIndex);
                    process.addFrame(frame);
                    frame.assignProcess(process);
                    frameIndex++;
                }
            }

            List<Frame> remainingFrames = frames.stream().filter(Frame::isFree).collect(Collectors.toCollection(ArrayList::new));
            processList.sort(Comparator.comparingDouble(p -> getFramesForProcessRemainder(p, totalPageNumber)));

            for (int i = 0; i < remainingFrames.size(); i++) {
                Frame frame = remainingFrames.get(i);
                frame.assignProcess(processList.get(i));
                processList.get(i).addFrame(frame);
            }

            hasAssignedFrames = true;
        }
    }
}
