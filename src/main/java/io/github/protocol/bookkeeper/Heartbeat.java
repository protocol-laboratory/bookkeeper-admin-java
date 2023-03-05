package io.github.protocol.bookkeeper;

public interface Heartbeat {
    void heartbeat() throws BookkeeperAdminException;
}
