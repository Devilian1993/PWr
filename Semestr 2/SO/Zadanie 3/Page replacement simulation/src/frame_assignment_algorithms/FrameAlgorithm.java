package frame_assignment_algorithms;

import models.Frame;
import models.Process;

import java.util.List;

public abstract class FrameAlgorithm {
    boolean hasAssignedFrames;
    List<Frame> frames;

    public FrameAlgorithm(List<Frame> frames) {
        this.hasAssignedFrames = false;
        this.frames = frames;
    }

    int framesSize() {
        return frames.size();
    }

    public abstract void assignFrames(List<Process> processList);
}
