package io.github.protocol.bookkeeper.admin.jdk;

public interface BookkeeperAdmin {
    static BookkeeperAdminBuilder builder() {
        return new BookkeeperAdminBuilderImpl();
    }

    Bookies bookies();

    AutoRecovery autoRecovery();

    Heartbeat heartbeat();
}
