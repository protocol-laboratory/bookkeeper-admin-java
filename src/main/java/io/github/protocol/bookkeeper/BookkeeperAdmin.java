package io.github.protocol.bookkeeper;

public interface BookkeeperAdmin {
    static BookkeeperAdminBuilder builder() {
        return new BookkeeperAdminBuilderImpl();
    }

    Heartbeat heartbeat();
}
