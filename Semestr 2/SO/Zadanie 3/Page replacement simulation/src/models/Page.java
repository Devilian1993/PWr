package models;

public class Page {
    private final int id;
    private int numberOfUses;
    private int lastUseTime;
    private int loadTime;
    private boolean referenceBit;
    private final Process process;

    public Page(int id) {
        this.id = id;
        numberOfUses = 0;
        lastUseTime = 0;
        loadTime = 0;
        referenceBit = true;
        process = null;
    }

    public Page(int id, Process process) {
        this.id = id;
        numberOfUses = 0;
        lastUseTime = 0;
        loadTime = 0;
        referenceBit = true;
        this.process = process;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public int getLastUseTime() {
        return lastUseTime;
    }

    public int getLoadTime() {
        return loadTime;
    }

    public boolean isReferenceBit() {
        return referenceBit;
    }

    public void setNumberOfUses(int numberOfUses) {
        this.numberOfUses = numberOfUses;
    }

    public void setLastUseTime(int lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public void setLoadTime(int loadTime) {
        this.loadTime = loadTime;
    }

    public void setReferenceBit(boolean referenceBit) {
        this.referenceBit = referenceBit;
    }

    @Override
    public String toString() {
        assert process != null;
        return "Page{" +
                "id=" + process.getId() + id +
                ", uses=" + numberOfUses +
                ", lastUse=" + lastUseTime +
                ", loadT=" + loadTime +
                ", refBit=" + referenceBit +
                '}';
    }
}
