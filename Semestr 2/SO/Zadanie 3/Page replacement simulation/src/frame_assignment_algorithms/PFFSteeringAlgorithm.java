package frame_assignment_algorithms;


import models.Frame;
import models.Process;

import java.util.List;

public class PFFSteeringAlgorithm extends FrameAlgorithm {

    private final int ASSIGN_NEW_FRAME_PFF_THRESHOLD;
    private final int FREE_FRAME_PFF_THRESHOLD;

    public PFFSteeringAlgorithm(List<Frame> frames, int new_frame, int free_frame) {
        super(frames);
        ASSIGN_NEW_FRAME_PFF_THRESHOLD = new_frame;
        FREE_FRAME_PFF_THRESHOLD = free_frame;
    }

    public PFFSteeringAlgorithm(int new_frame, int free_frame) {
        ASSIGN_NEW_FRAME_PFF_THRESHOLD = new_frame;
        FREE_FRAME_PFF_THRESHOLD = free_frame;
    }

    @Override
    public void assignFrames(List<Process> processList) {
        if (!hasAssignedFrames) {
            new ProportionalAlgorithm(frames).assignFrames(processList);
            hasAssignedFrames = true;
        } else {
            for (Process process : processList) {
                int numberOfPageFaults = process.getPageFaultCountInWindow();

                if (numberOfPageFaults != 0) {
                    //System.out.println("TEST");
                }

                if (numberOfPageFaults >= ASSIGN_NEW_FRAME_PFF_THRESHOLD) {
                    boolean assignedFrame = false;
                    for (Frame frame : frames) {
                        if (frame.isFree()) {
                            assignedFrame = true;
                            frame.assignProcess(process);
                            process.addFrame(frame);
                            break;
                        }
                    }

                    if (!assignedFrame) {
                        process.setHalted(true);
                    }

                } else if (numberOfPageFaults <= FREE_FRAME_PFF_THRESHOLD && process.getFrameSet().size() > 2) {
                    process.freeFrame();
                }
            }
        }
    }
}
