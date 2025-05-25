package frame_assignment_algorithms;

import models.Frame;
import models.Process;

import java.util.List;

public class ProportionalAlgorithm extends FrameAlgorithm {
    public ProportionalAlgorithm(List<Frame> frames) {
        super(frames);
    }

    private int getFramesForProcess(Process process, int totalPageNumber) {
        return process.getPageSetSize()  * frames.size() / totalPageNumber;
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
                if (framesForProcess == 0) {
                    System.out.printf("PROCES O ID %d NIE OTRZYMAŁ ŻADNEJ RAMKI\n", process.getId());
                }
                for (int i = 0; i < framesForProcess; i++) {
                    Frame frame = frames.get(frameIndex);
                    process.addFrame(frame);
                    frame.assignProcess(process);
                    frameIndex++;
                }
            }
            hasAssignedFrames = true;
        }
    }
}
