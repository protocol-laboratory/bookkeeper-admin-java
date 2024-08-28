package io.github.protocol.bookkeeper;

public class GarbageCollectionStatus {

    private boolean forceCompacting;

    private boolean majorCompacting;

    private boolean minorCompacting;

    private long lastMajorCompactionTime;

    private long lastMinorCompactionTime;

    private int majorCompactionCounter;

    private int minorCompactionCounter;

    public boolean isForceCompacting() {
        return forceCompacting;
    }

    public void setForceCompacting(boolean forceCompacting) {
        this.forceCompacting = forceCompacting;
    }

    public boolean isMajorCompacting() {
        return majorCompacting;
    }

    public void setMajorCompacting(boolean majorCompacting) {
        this.majorCompacting = majorCompacting;
    }

    public boolean isMinorCompacting() {
        return minorCompacting;
    }

    public void setMinorCompacting(boolean minorCompacting) {
        this.minorCompacting = minorCompacting;
    }

    public long getLastMajorCompactionTime() {
        return lastMajorCompactionTime;
    }

    public void setLastMajorCompactionTime(long lastMajorCompactionTime) {
        this.lastMajorCompactionTime = lastMajorCompactionTime;
    }

    public long getLastMinorCompactionTime() {
        return lastMinorCompactionTime;
    }

    public void setLastMinorCompactionTime(long lastMinorCompactionTime) {
        this.lastMinorCompactionTime = lastMinorCompactionTime;
    }

    public int getMajorCompactionCounter() {
        return majorCompactionCounter;
    }

    public void setMajorCompactionCounter(int majorCompactionCounter) {
        this.majorCompactionCounter = majorCompactionCounter;
    }

    public int getMinorCompactionCounter() {
        return minorCompactionCounter;
    }

    public void setMinorCompactionCounter(int minorCompactionCounter) {
        this.minorCompactionCounter = minorCompactionCounter;
    }
}
