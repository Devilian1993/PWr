package algorithms;

import models.Frame;
import models.Page;

import java.util.List;

public class LRUAprox extends Algorithm {
    public LRUAprox() {
        super();
    }

    private Frame frameToInsert() {
        int index = 0;
        while (true) {
            Frame currentFrame = frames.get(index);
            Page pageInCurrentFrame = currentFrame.getPage();

            if (!pageInCurrentFrame.isReferenceBit()) {
                int foundVictimIndex = index;
                return frames.get(foundVictimIndex);
            } else {
                pageInCurrentFrame.setReferenceBit(false);
                index = (index + 1) % frames.size();
            }
        }
    }

    @Override
    public void replacePage(Page newPage) {
        Frame newFrame = frameToInsert();
        newFrame.setPage(newPage);
    }
}
