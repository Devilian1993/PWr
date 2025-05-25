package frame_assignment_algorithms;

import models.Frame;
import models.Process;

import java.util.List;

public class EqualAlgorithm extends FrameAlgorithm {
    public EqualAlgorithm(List<Frame> frames) {
        super(frames);
    }

    private int getFramesPerProcess(List<Process> processList) {
        return framesSize() / processList.size();
    }

    @Override
    public void assignFrames(List<Process> processList) {
        if (!hasAssignedFrames) {
            if (processList == null || processList.isEmpty() || framesSize() == 0) {
                throw new IllegalArgumentException();
            }

            int numProcesses = processList.size();

            int baseFramesPerProcess = getFramesPerProcess(processList);
            int remainderFrames = framesSize() % numProcesses;

            int frameIndex = 0;

            for (int i = 0; i < numProcesses; i++) {
                Process currentProcess = processList.get(i);
                int framesToAssignToThisProcess = baseFramesPerProcess;
                if (i < remainderFrames) {
                    framesToAssignToThisProcess++;
                }

                for (int j = 0; j < framesToAssignToThisProcess; j++) {
                    Frame frame = this.frames.get(frameIndex);
                    currentProcess.addFrame(frame);
                    frame.assignProcess(currentProcess);
                    frameIndex++;
                }
            }

            hasAssignedFrames = true;
        }
    }
}
