package io.github.protocol.bookkeeper;

public interface BookkeeperAdmin {
    static BookkeeperAdminBuilder builder() {
        return new BookkeeperAdminBuilderImpl();
    }

    Bookies bookies();

    AutoRecovery autoRecovery();

    Heartbeat heartbeat();
}
