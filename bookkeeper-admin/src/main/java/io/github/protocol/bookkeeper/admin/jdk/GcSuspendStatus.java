package io.github.protocol.bookkeeper.admin.jdk;

public class GcSuspendStatus {
    private boolean isMajorGcSuspended;

    private boolean isMinorGcSuspended;

    public boolean isMajorGcSuspended() {
        return isMajorGcSuspended;
    }

    public void setMajorGcSuspended(boolean majorGcSuspended) {
        isMajorGcSuspended = majorGcSuspended;
    }

    public boolean isMinorGcSuspended() {
        return isMinorGcSuspended;
    }

    public void setMinorGcSuspended(boolean minorGcSuspended) {
        isMinorGcSuspended = minorGcSuspended;
    }

    public GcSuspendStatus(){}

    public GcSuspendStatus(boolean isMajorGcSuspended, boolean isMinorGcSuspended) {
        this.isMajorGcSuspended = isMajorGcSuspended;
        this.isMinorGcSuspended = isMinorGcSuspended;
    }
}
