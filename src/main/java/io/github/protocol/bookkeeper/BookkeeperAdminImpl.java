package io.github.protocol.bookkeeper;

public class BookkeeperAdminImpl implements BookkeeperAdmin {
    private final Heartbeat heartbeat;
    BookkeeperAdminImpl(Configuration conf) {
        InnerHttpClient innerHttpClient = new InnerHttpClient(conf);
        this.heartbeat = new HeartbeatImpl(innerHttpClient);
    }

    @Override
    public Heartbeat heartbeat() {
        return heartbeat;
    }
}
