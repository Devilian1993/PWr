package algorithms;

import models.Frame;
import models.Page;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class LRU extends Algorithm {
    public LRU() {
        super();
    }

    private Frame frameToInsert() {
        return frames.stream().min(Comparator.comparingInt(f -> f.getPage().getLastUseTime())).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void replacePage(Page newPage) {
        Frame newFrame = frameToInsert();
        newFrame.setPage(newPage);
    }
}
