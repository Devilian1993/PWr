package models;

public class Frame {
    private Page page;

    public Frame(Page page) {
        this.page = page;
    }

    public Frame() {
        this.page = null;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }
}
