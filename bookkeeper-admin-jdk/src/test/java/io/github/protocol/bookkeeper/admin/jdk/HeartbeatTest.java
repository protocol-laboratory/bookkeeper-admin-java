package io.github.protocol.bookkeeper.admin.jdk;

import io.github.embedded.bookkeeper.core.EmbeddedBookkeeperServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HeartbeatTest {
    private static final EmbeddedBookkeeperServer SERVER = new EmbeddedBookkeeperServer();

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }

    @Test
    public void testHeartbeat() throws BookkeeperAdminException {
        BookkeeperAdmin bookkeeperAdmin = BookkeeperAdmin.builder().port(SERVER.getBkWebPort()).build();
        bookkeeperAdmin.heartbeat().heartbeat();
    }
}
