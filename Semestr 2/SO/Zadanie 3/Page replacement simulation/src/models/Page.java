package models;

public class Page {
    private final int id;
    private int numberOfUses;
    private int lastUseTime;
    private int loadTime;
    private boolean referenceBit;

    public Page(int id) {
        this.id = id;
        numberOfUses = 0;
        lastUseTime = 0;
        loadTime = 0;
        referenceBit = true;
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
        return "Page{" +
                "id=" + id +
                ", uses=" + numberOfUses +
                ", lastUse=" + lastUseTime +
                ", loadT=" + loadTime +
                ", refBit=" + referenceBit +
                '}';
    }
}
