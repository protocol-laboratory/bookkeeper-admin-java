package io.github.protocol.bookkeeper;

public class BookieStatus {
    private boolean running;

    private boolean readOnly;

    private boolean shuttingDown;

    private boolean availableForHighPriorityWrites;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isShuttingDown() {
        return shuttingDown;
    }

    public void setShuttingDown(boolean shuttingDown) {
        this.shuttingDown = shuttingDown;
    }

    public boolean isAvailableForHighPriorityWrites() {
        return availableForHighPriorityWrites;
    }

    public void setAvailableForHighPriorityWrites(boolean availableForHighPriorityWrites) {
        this.availableForHighPriorityWrites = availableForHighPriorityWrites;
    }
}
