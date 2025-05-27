package models;

import generators.Generator;

import java.util.*;

public class Process {
    private final int id;
    private final int pageSetSize;
    private final List<Page> pageSet;
    private final List<Frame> frameSet;
    private boolean isHalted;
    private final List<Page> requestsList;

    private final List<Integer> pageFaultList;
    private final List<Integer> runningProcessPageFaultList;
    private final int pffTimeWindow;
    private final int wssTimeWindow;
    private int pageFaultCountInWindow;
    private int wssInWindow;

    public Process(int id, int pageSetSize, int pffTimeWindow, int wssTimeWindow) {
        this.id = id;
        this.pageSetSize = pageSetSize;
        this.pageSet = new ArrayList<>();
        this.frameSet = new ArrayList<>();
        this.isHalted = false;
        this.requestsList = new ArrayList<>();
        this.pageFaultList = new ArrayList<>();
        this.pffTimeWindow = pffTimeWindow;
        this.wssTimeWindow = wssTimeWindow;
        this.pageFaultCountInWindow = 0;
        this.runningProcessPageFaultList = new ArrayList<>();
    }

    public void generateRequestsList(Generator generator, int numberOfRequests) {
        generator.publicGeneratePagesSet(pageSet, pageSetSize, this);
        generator.generateRequestsFromPageSet(pageSet, numberOfRequests, requestsList, this);
    }

    public boolean isHalted() {
        return isHalted;
    }

    public void setHalted(boolean halted) {
        isHalted = halted;

        if (halted) {
            freeAllFrames();
        }
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

    public List<Page> getRequestsList() {
        return requestsList;
    }

    public Iterator<Page> getRequestsIterator() {
        return requestsList.iterator();
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
            runningProcessPageFaultList.add(1);
        } else {
            pageFaultList.add(0);
            runningProcessPageFaultList.add(0);
        }
    }

    public void emptyStep() {
        pageFaultList.add(0);
    }

    //public void calculateNumberOfPageFaultsInWindow(int globalTime) {
    //    if (globalTime == 0) {
    //        return;
    //    }
//
    //    int end = Math.min(globalTime, pageFaultList.size());
    //    int start = Math.max(0, end - pffTimeWindow);
//
    //    pageFaultCountInWindow = pageFaultList.subList(start, end).stream()
    //            .mapToInt(Integer::intValue)
    //            .sum();
    //}

    public void calculateNumberOfPageFaultsInWindow() {
        int size = runningProcessPageFaultList.size();
        if (size < pffTimeWindow) {
            pageFaultCountInWindow = runningProcessPageFaultList.stream().reduce(0, Integer::sum);
            return;
        }

        // Bierzemy ostatnie N wpisów
        int start = size - pffTimeWindow;
        int end = size;

        pageFaultCountInWindow = runningProcessPageFaultList.subList(start, end).stream().reduce(0, Integer::sum);
    }



    public int getPageFaultCountInWindow() {
        return pageFaultCountInWindow;
    }

    public void calculateWSSInWindow(int globalTime) {
        int windowStart = Math.max(0, globalTime - wssTimeWindow); // np. 10
        Set<Page> uniquePages = new HashSet<>();

        int end = Math.min(windowStart + wssTimeWindow, requestsList.size());

        if (windowStart > end) {
            return;
        }

        List<Page> windowRequests = requestsList.subList(windowStart, end);

        for (Page page : windowRequests) {
            if (page.getLastUseTime() >= windowStart && page.getLastUseTime() <= globalTime) {
                uniquePages.add(page);  // lub uniquePageIds.add(page) jeśli Page ma dobrze zdefiniowane equals/hashCode
            }
        }

        this.wssInWindow = uniquePages.size();
    }

    public int getWSS() {
        return wssInWindow;
    }

    public void freeAllFrames() {
        frameSet.forEach(Frame::free);
        frameSet.clear();
    }
}
