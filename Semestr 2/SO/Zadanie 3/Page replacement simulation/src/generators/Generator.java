package generators;

import models.Frame;
import models.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private List<Page> localPages;
    private List<Page> nonLocalPages;
    private Random rand;

    private final double LOCAL_PAGE_PROBABILITY = 0.9;

    public Generator() {
        this.localPages = new ArrayList<Page>();
        this.nonLocalPages = new ArrayList<Page>();
        this.rand = new Random();
    }

    private void generatePagesSet(int uniquePages) {
        for (int i = 0; i < uniquePages/5*4; i++) {
            localPages.add(new Page(i));
        }
        for (int i = uniquePages/5*4; i < uniquePages; i++) {
            nonLocalPages.add(new Page(i));
        }
    }

    public void generateRequests(List<Page> simulationPages, int totalPages, int uniquePages) {
        generatePagesSet(uniquePages);

        for (int i = 0; i < totalPages; i++) {
            if (rand.nextDouble() < LOCAL_PAGE_PROBABILITY) {
                simulationPages.add(localPages.get(rand.nextInt(localPages.size())));
            } else {
                simulationPages.add(nonLocalPages.get(rand.nextInt(nonLocalPages.size())));
            }
        }
    }

    public void generateFrames(List<Frame> simulationFrames, int uniqueFrames) {
        for (int i = 0; i < uniqueFrames; i++) {
            simulationFrames.add(new Frame());
        }
    }
}
