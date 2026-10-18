package frame_assignment_algorithms;


import models.Frame;
import models.Process;

import java.util.List;

public class PFFSteeringAlgorithm extends FrameAlgorithm {

    private final int ASSIGN_NEW_FRAME_PFF_THRESHOLD;
    private final int FREE_FRAME_PFF_THRESHOLD;
    private final int REMOVE_FRAME_THRESHHOLD;

    public PFFSteeringAlgorithm(List<Frame> frames, int new_frame, int free_frame) {
        super(frames);
        ASSIGN_NEW_FRAME_PFF_THRESHOLD = new_frame;
        FREE_FRAME_PFF_THRESHOLD = free_frame;
        REMOVE_FRAME_THRESHHOLD = ASSIGN_NEW_FRAME_PFF_THRESHOLD + 2;
    }

    public PFFSteeringAlgorithm(int new_frame, int free_frame) {
        ASSIGN_NEW_FRAME_PFF_THRESHOLD = new_frame;
        FREE_FRAME_PFF_THRESHOLD = free_frame;
        REMOVE_FRAME_THRESHHOLD = ASSIGN_NEW_FRAME_PFF_THRESHOLD;
    }

    @Override
    public void assignFrames(List<Process> processList) {
        if (!hasAssignedFrames) {
            //System.out.println("PFF: Initializing with ProportionalAlgorithm.");
            new ProportionalAlgorithm(frames).assignFrames(processList);
            for (Process p : processList) {
                //System.out.println("  Process " + p.getId() + " initial frames: " + p.getFrameSet().size());
            }
            hasAssignedFrames = true;
        } else {
            //System.out.println("\n FREE FRAMES: " + frames.stream().filter(Frame::isFree).count());
            for (Process process : processList) {
                if (process.isHalted()) {
                    for (Frame frame: frames) {
                        if (frame.isFree()) {
                            process.addFrame(frame);
                            frame.assignProcess(process);
                            process.setHalted(false);
                        }
                    }
                }

                int numberOfPageFaults = process.getPageFaultCountInWindow();

                //System.out.println("\n[PFF DEBUG] Process ID: " + process.getId());
                //System.out.println("\nPAGE FAULTS: " + numberOfPageFaults);
                //System.out.println("  Is Halted: " + process.isHalted());

                if (numberOfPageFaults >= ASSIGN_NEW_FRAME_PFF_THRESHOLD) {
                    boolean assignedFrame = false;
                    for (Frame frame : frames) {
                        if (frame.isFree() && !process.hasEmptyFrame()) {
                            //System.out.println("    SUCCESS: Assigning free frame to Process " + process.getId());
                            assignedFrame = true;
                            frame.assignProcess(process);
                            process.addFrame(frame);
                            //System.out.println("    Process " + process.getId() + " now has " + process.getFrameSet().size() + " frames.");
                            break;
                        }
                    }

                    //System.out.println("    FAILURE: No free frame found in the system to assign.");
                    if (!assignedFrame && !process.hasEmptyFrame()) {
                        process.setHalted(true);
                    }
                } else if ((numberOfPageFaults <= FREE_FRAME_PFF_THRESHOLD && process.getFrameSet().size() > 1 && process.moreThanPffRequests()) || process.getFrameSet().size() > process.getPageSetSize()) {
                    //System.out.println("  Condition: Low PFF & excess frames. Attempting to free frame.");
                    process.freeFrame();
                }
            }
        }
    }
}
