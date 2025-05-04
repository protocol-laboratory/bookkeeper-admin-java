package io.github.protocol.bookkeeper.admin.jdk;

import org.junit.jupiter.api.Test;

public class HeartbeatTest extends BaseTest {
    @Test
    public void testHeartbeat() throws BookkeeperAdminException {
        BookkeeperAdmin bookkeeperAdmin = BookkeeperAdmin.builder().port(server.getBkWebPort()).build();
        bookkeeperAdmin.heartbeat().heartbeat();
    }
}
