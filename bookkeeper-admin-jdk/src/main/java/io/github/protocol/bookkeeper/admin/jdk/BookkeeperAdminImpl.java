package io.github.protocol.bookkeeper.admin.jdk;

import io.github.protocol.bookkeeper.admin.api.Configuration;

public class BookkeeperAdminImpl implements BookkeeperAdmin {
    private final BookiesImpl bookies;

    private final AutoRecovery autoRecovery;

    private final Heartbeat heartbeat;
    BookkeeperAdminImpl(Configuration conf) {
        InnerHttpClient innerHttpClient = new InnerHttpClient(conf);
        this.bookies = new BookiesImpl(innerHttpClient);
        this.autoRecovery = new AutoRecoveryImpl(innerHttpClient);
        this.heartbeat = new HeartbeatImpl(innerHttpClient);
    }

    @Override
    public Bookies bookies() {
        return bookies;
    }

    @Override
    public AutoRecovery autoRecovery() {
        return autoRecovery;
    }

    @Override
    public Heartbeat heartbeat() {
        return heartbeat;
    }
}
