package frame_assignment_algorithms;


import models.Frame;
import models.Process;

import java.util.List;

public class PFFSteeringAlgorithm extends FrameAlgorithm {

    private final int ASSIGN_NEW_FRAME_PFF_THRESHOLD = 15;
    private final int FREE_FRAME_PFF_THRESHOLD = 0;

    public PFFSteeringAlgorithm(List<Frame> frames) {
        super(frames);
    }

    public PFFSteeringAlgorithm() {
    }

    @Override
    public void assignFrames(List<Process> processList) {
        if (!hasAssignedFrames) {
            new ProportionalAlgorithm(frames).assignFrames(processList);
            hasAssignedFrames = true;
        } else {
            for (Process process : processList) {
                int numberOfPageFaults = process.getPageFaultCountInWindow();

                if (numberOfPageFaults >= ASSIGN_NEW_FRAME_PFF_THRESHOLD) {
                    boolean assignedFrame = false;
                    for (Frame frame : frames) {
                        if (frame.isFree()) {
                            assignedFrame = true;
                            frame.assignProcess(process);
                            break;
                        }
                    }

                    if (!assignedFrame) {
                        process.setHalted(true);
                    }

                } else if (numberOfPageFaults <= FREE_FRAME_PFF_THRESHOLD && process.getFrameSet().size() > 1) {
                    process.freeFrame();
                }
            }
        }
    }
}
