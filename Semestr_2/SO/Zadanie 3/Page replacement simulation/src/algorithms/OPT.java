package algorithms;

import models.Frame;
import models.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class OPT extends Algorithm {

    private List<Page> futurePages;

    public OPT() {
        super();
    }

    public void setFuturePages(List<Page> futurePages) {
        this.futurePages = futurePages;
    }

    private Frame frameToInsert() {
        List<Page> pagesInFrames = frames.stream().map(Frame::getPage).collect(Collectors.toCollection(ArrayList::new));
        int maxIndex = Integer.MIN_VALUE;
        Page maxPage = null;

        for (Page page : pagesInFrames) {
            int currentLastIndex = -1;
            for (int i = 0; i < futurePages.size(); i++) {
                if (page == futurePages.get(i)) {
                    currentLastIndex = i;
                    break;
                }
            }

            if (currentLastIndex == -1) {
                maxPage = page;
                break;
            }

            if (currentLastIndex > maxIndex) {
                maxIndex = currentLastIndex;
                maxPage = page;
            }
        }

        Page finalMaxPage = maxPage;
        return frames.stream().filter(f -> f.getPage() == finalMaxPage).findFirst().get();
    }

    @Override
    public void replacePage(Page newPage) {
        Frame frameToInsert = null;
        try {
            frameToInsert = frameToInsert();
        } catch (Exception e) {
            throw new NoSuchElementException("Nie znaleziono ramki do wymiany!");
        }
        frameToInsert.setPage(newPage);
    }
}
