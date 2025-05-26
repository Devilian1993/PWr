package frame_assignment_algorithms;

import models.Frame;
import models.Process;

import java.util.ArrayList;
import java.util.List;

public abstract class FrameAlgorithm {
    boolean hasAssignedFrames;
    List<Frame> frames;

    public FrameAlgorithm(List<Frame> frames) {
        this.hasAssignedFrames = false;
        this.frames = frames;
    }

    public FrameAlgorithm() {
        this.hasAssignedFrames = false;
        this.frames = null;
    }

    public void setFrames(List<Frame> frames) {
        if (this.frames == null) {
            this.frames = frames;
        } else {
            throw new UnsupportedOperationException("You cant set frames after they have been set");
        }
    }

    int framesSize() {
        return frames.size();
    }

    public abstract void assignFrames(List<Process> processList);
}
