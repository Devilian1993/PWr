package models;

import generators.Generator;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private final int id;
    private final int pageSetSize;
    private final List<Page> pageSet;
    private final List<Frame> frameSet;
    private boolean isHalted;
    private final List<Page> requestsList;

    public Process(int id, int pageSetSize) {
        this.id = id;
        this.pageSetSize = pageSetSize;
        this.pageSet = new ArrayList<>();
        this.frameSet = new ArrayList<>();
        this.isHalted = false;
        this.requestsList = new ArrayList<>();
    }

    public void generateRequestsList(Generator generator, int numberOfRequests) {
        generator.publicGeneratePagesSet(pageSet, pageSetSize);
        generator.generateRequestsFromPageSet(pageSet, numberOfRequests, requestsList);
    }

    public boolean isHalted() {
        return isHalted;
    }

    public void setHalted(boolean halted) {
        isHalted = halted;
    }

    public List<Frame> getFrameSet() {
        return frameSet;
    }

    public List<Page> getPageSet() {
        return pageSet;
    }

    public int getPageSetSize() {
        return pageSetSize;
    }
}
