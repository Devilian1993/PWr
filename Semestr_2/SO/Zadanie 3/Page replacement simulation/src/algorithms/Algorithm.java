package algorithms;

import models.Frame;
import models.Page;

import java.util.List;

public abstract class Algorithm {
    List<Frame> frames;

    public Algorithm() {
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    abstract public void replacePage(Page newPage);
}
