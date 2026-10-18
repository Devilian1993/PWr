package algorithms;

import models.Frame;
import models.Page;

import java.util.Comparator;
import java.util.List;

public class FIFO extends Algorithm {
    public FIFO() {
        super();
    }

    @Override
    public void replacePage(Page newPage) {
        Frame frame = frames.stream().
                filter(f -> f.getPage() != null).
                min(Comparator.comparingInt(f -> f.getPage().getLoadTime())).
                orElse(null);

        if (frame != null) {
            frame.setPage(newPage);
        } else {
            throw new NullPointerException("Nie znaleziono ramki do wstawienia strony!");
        }
    }
}
