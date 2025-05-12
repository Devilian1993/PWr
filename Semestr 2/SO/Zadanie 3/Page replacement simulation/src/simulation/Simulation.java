package simulation;

import algorithms.Algorithm;
import algorithms.OPT;
import models.Frame;
import models.Page;
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

    public Simulation(SimulationConfig config) {
        this.requestsList = new ArrayList<>();
        this.frameList = new ArrayList<>();
        this.algorithm = config.getAlgorithm();
        this.pageFaults = 0;
        this.thrashingCount = 0;

        Generator generator = new Generator();
        generator.generateRequests(requestsList, config.getTotalRequestsCount(), config.getUniquePageNumber());
        generator.generateFrames(frameList, config.getFramesNumber());

        algorithm.setFrames(frameList);
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
        int timer = 0;
        for (int i = 0; i < requestsList.size(); i++) {
            Page request = requestsList.get(i);
            request.setLastUseTime(timer);
            request.setReferenceBit(true);
            if (isPageInFrame(request)) {
                continue;
            } else if(anyEmptyFrame()) {
                addToEmptyFrame(request);
                pageFaults++;
                request.setLoadTime(timer);
            } else {
                if (algorithm instanceof OPT) {
                    ((OPT) algorithm).setFuturePages(requestsList.subList(i+1, requestsList.size()));
                }
                algorithm.replacePage(request);
                pageFaults++;
                request.setLoadTime(timer);
            }
            timer++;
        }
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
}
