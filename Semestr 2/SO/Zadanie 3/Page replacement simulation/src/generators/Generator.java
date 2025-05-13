package generators;

import models.Frame;
import models.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private List<Page> localPages1;
    private List<Page> localPages2;
    private List<Page> nonLocalPages;
    private Random rand;

    private final double LOCAL_PAGE_PROBABILITY = 0.9;

    public Generator(int generatorSeed) {
        this.localPages1 = new ArrayList<Page>();
        this.localPages2 = new ArrayList<Page>();
        this.nonLocalPages = new ArrayList<Page>();
        this.rand = new Random(generatorSeed);
    }

    private void generatePagesSet(int uniquePages) {
        for (int i = 0; i < uniquePages/3; i++) {
            localPages1.add(new Page(i));
        }
        for (int i = uniquePages/3; i < uniquePages/3*2; i++) {
            localPages2.add(new Page(i));
        }
        for (int i = uniquePages/3*2; i < uniquePages; i++) {
            nonLocalPages.add(new Page(i));
        }
    }

    public void generateRequests(List<Page> simulationPages, int totalPages, int uniquePages) {
        generatePagesSet(uniquePages);
        int counter = 0;
        List<Page> currentSet = localPages1;
        List<Page> otherSet = localPages2;

        for (int i = 0; i < totalPages; i++) {
            if (rand.nextDouble() < LOCAL_PAGE_PROBABILITY) {
                if (counter == 200) {
                    List<Page> temp = localPages1;
                    currentSet = otherSet;
                    otherSet = temp;
                    counter = 0;
                }
                simulationPages.add(currentSet.get(rand.nextInt(currentSet.size())));
                counter++;
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
