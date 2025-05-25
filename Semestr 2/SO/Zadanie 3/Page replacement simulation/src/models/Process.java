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

    private final List<Integer> pageFaultList;
    private final int pffTimeWindow;
    private int pageFaultCountInWindow;
    private int wssInWindow;

    public Process(int id, int pageSetSize, int pffTimeWindow) {
        this.id = id;
        this.pageSetSize = pageSetSize;
        this.pageSet = new ArrayList<>();
        this.frameSet = new ArrayList<>();
        this.isHalted = false;
        this.requestsList = new ArrayList<>();
        this.pageFaultList = new ArrayList<>();
        this.pffTimeWindow = pffTimeWindow;
        this.pageFaultCountInWindow = 0;
    }

    public void generateRequestsList(Generator generator, int numberOfRequests) {
        generator.publicGeneratePagesSet(pageSet, pageSetSize, this);
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

    public int getId() {
        return id;
    }

    public void addFrame(Frame frame) {
        frameSet.add(frame);
    }

    public void freeFrame() {
        frameSet.getLast().free();
        frameSet.removeLast();
    }

    public int getPffTimeWindow() {
        return pffTimeWindow;
    }

    public void addPageFault(boolean pageFaultOccured) {
        if (pageFaultOccured) {
            pageFaultList.add(1);
        } else {
            pageFaultList.add(0);
        }
    }

    public void calculateNumberOfPageFaultsInWindow(int globalTime) {
        pageFaultCountInWindow =  pageFaultList.subList(globalTime, globalTime + pffTimeWindow).stream().reduce(0, Integer::sum);
    }

    public int getPageFaultCountInWindow() {
        return pageFaultCountInWindow;
    }

    public void calculateWSSInWindow(int globalTime) {
        wssInWindow = (int) requestsList.subList(globalTime, globalTime + pffTimeWindow).stream().distinct().count();
    }

    public int getWSS() {
        return wssInWindow;
    }
}
