package io.github.protocol.bookkeeper;

import io.github.embedded.bookkeeper.core.EmbeddedBookkeeperServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AutoRecoveryImplTest {

    private static final EmbeddedBookkeeperServer SERVER = new EmbeddedBookkeeperServer();

    private static AutoRecoveryImpl autoRecovery = null;

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        Configuration conf = new Configuration();
        conf.setHost("localhost");
        conf.setPort(SERVER.getBkWebPort());
        autoRecovery = new AutoRecoveryImpl(new InnerHttpClient(conf));
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }
}
