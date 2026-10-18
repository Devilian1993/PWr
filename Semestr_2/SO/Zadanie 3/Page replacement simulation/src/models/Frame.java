package models;

public class Frame {
    private Page page;
    private Process process;

    public Frame(Page page) {
        this.page = page;
        this.process = null;
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

    public void assignProcess(Process process) {
        this.process = process;
    }

    public void free() {
        this.process = null;
        this.page = null;
    }

    public boolean isFree() {
        return this.process == null && page == null;
    }
}
