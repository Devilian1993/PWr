package simulation;

import algorithms.Algorithm;
import algorithms.OPT;
import models.Frame;
import models.Page;
import models.Process;
import generators.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Simulation {

    private List<Page> requestsList;
    private List<Frame> frameList;
    private Algorithm algorithm;
    private int pageFaults;
    private int thrashingCount;
    private final Process process;

    private final int THRASHING_TIME_WINDOW;
    private final int THRASHING_THRESHOLD;
    private ArrayList<Integer> pageFaultsArray;

    public Simulation(SimulationConfig config) {
        this.requestsList = new ArrayList<>();
        this.frameList = new ArrayList<>();
        this.algorithm = config.getAlgorithm();
        this.pageFaults = 0;
        this.thrashingCount = 0;

        Generator generator = new Generator(config.getGeneratorSeed());
        generator.generateRequests(requestsList, config.getTotalRequestsCount(), config.getUniquePageNumber());
        generator.generateFrames(frameList, config.getFramesNumber());

        algorithm.setFrames(frameList);

        THRASHING_TIME_WINDOW = 20;
        THRASHING_THRESHOLD = 12;
        pageFaultsArray = new ArrayList<>();

        this.process = null;
    }

    public Simulation(Process process, Algorithm algorithm) {
        this.requestsList = process.getRequestsList();
        this.frameList = process.getFrameSet();
        this.algorithm = algorithm;
        this.thrashingCount = 0;

        THRASHING_TIME_WINDOW = 10;
        THRASHING_THRESHOLD = 4;
        this.pageFaultsArray = new ArrayList<>();

        this.process = process;
    }

    private boolean isPageInFrame(Page page) {
        return frameList.stream().anyMatch(frame -> frame.getPage() == page);
    }

    private boolean anyEmptyFrame() {
        return frameList.stream().anyMatch(f -> f.getPage() == null);
    }

    private void addToEmptyFrame(Page page) {
        frameList.stream().filter(f -> f.getPage() == null).findFirst().orElseThrow(NoSuchElementException::new).setPage(page);
    }

    public void run() {
        if (process != null) {
            throw new UnsupportedOperationException("Run function is to be used only as a full simulation of a page replacement" +
                    "algorithms, not as a virtual simulation in a multiprocess environment.");
        }
        int timer = 0;
        for (int i = 0; i < requestsList.size(); i++) {
            if (i >= THRASHING_TIME_WINDOW && (i % (THRASHING_TIME_WINDOW/2) == 0)) {
                if (pageFaultsArray.subList(i-THRASHING_TIME_WINDOW, i).stream().reduce(0, Integer::sum) > THRASHING_THRESHOLD) {
                    thrashingCount++;
                }
            }
            Page request = requestsList.get(i);
            if (isPageInFrame(request)) {
                pageFaultsArray.add(0);
            } else if(anyEmptyFrame()) {
                pageFaultsArray.add(1);
                addToEmptyFrame(request);
                pageFaults++;
                request.setLoadTime(timer);
            } else {
                pageFaultsArray.add(1);
                if (algorithm instanceof OPT) {
                    ((OPT) algorithm).setFuturePages(requestsList.subList(i+1, requestsList.size()));
                }
                algorithm.replacePage(request);
                pageFaults++;
                request.setLoadTime(timer);
            }
            request.setLastUseTime(timer);
            request.setReferenceBit(true);
            timer++;
        }
    }

    public void step(int globalTime, Page request) {
        if (process == null) {
            throw new UnsupportedOperationException("Step function is to be used only as an internal simulation" +
                    "with a reference to a single process");
        }
        if (process.isHalted() || process.getFrameSet().isEmpty()) {
            return;
        }
        algorithm.setFrames(process.getFrameSet());
        process.calculateWSSInWindow(globalTime);
        if (globalTime >= THRASHING_TIME_WINDOW && (globalTime % (THRASHING_TIME_WINDOW/2) == 0)) {
            process.calculateNumberOfPageFaultsInWindow();
            if (process.getPageFaultCountInWindow() >= THRASHING_THRESHOLD) {
                thrashingCount++;
            }
        }
        if (isPageInFrame(request)) {
            pageFaultsArray.add(0);
            process.addPageFault(false);
        } else if(anyEmptyFrame()) {
            pageFaultsArray.add(1);
            addToEmptyFrame(request);
            pageFaults++;
            request.setLoadTime(globalTime);
            process.addPageFault(true);
        } else {
            pageFaultsArray.add(1);
            if (algorithm instanceof OPT) {
                ((OPT) algorithm).setFuturePages(requestsList.subList(globalTime+1, requestsList.size()));
            }
            algorithm.replacePage(request);
            pageFaults++;
            request.setLoadTime(globalTime);
            process.addPageFault(true);
        }
        //if (globalTime % process.getPffTimeWindow() == 0) {
        //    process.calculateNumberOfPageFaultsInWindow(globalTime);
        //}
        request.setLastUseTime(globalTime);
        request.setReferenceBit(true);
    }

    public void emptyStep() {
        if (process == null) {
            throw new UnsupportedOperationException("Empty step function is to be used only as an internal simulation" +
                    "with a reference to a single process");
        }
        pageFaultsArray.add(0);
        process.emptyStep();
    }

    public void printResults() {
        String algName = algorithm.getClass().getSimpleName();

        System.out.printf("Symulacja: %s\n" +
                "Liczba bledow stron: %d\n" +
                "Liczba szamotań: %d\n", algName, pageFaults, thrashingCount);
    }

    public void printRequests() {
        for (Page page : requestsList) {
            System.out.printf("%d ", page.getId());
        }
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public int getThrashingCount() {
        return thrashingCount;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
