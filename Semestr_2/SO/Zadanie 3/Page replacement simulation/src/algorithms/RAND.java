package algorithms;

import models.Frame;
import models.Page;

import java.util.List;
import java.util.Random;

public class RAND extends Algorithm {
    public RAND() {
        super();
    }

    @Override
    public void replacePage(Page newPage) {
        Random rand = new Random();
        Frame frameToInsert = frames.get(rand.nextInt(frames.size()));
        frameToInsert.setPage(newPage);
    }
}
