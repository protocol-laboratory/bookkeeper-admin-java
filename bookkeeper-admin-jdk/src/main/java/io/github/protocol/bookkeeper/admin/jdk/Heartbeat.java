package io.github.protocol.bookkeeper.admin.jdk;

public interface Heartbeat {
    void heartbeat() throws BookkeeperAdminException;
}
