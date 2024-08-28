package io.github.protocol.bookkeeper.admin.jdk;

import io.github.embedded.bookkeeper.core.EmbeddedBookkeeperServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigsImplTest {


    private static final EmbeddedBookkeeperServer SERVER = new EmbeddedBookkeeperServer();

    private static ConfigsImpl configsImpl = null;

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        Configuration conf = new Configuration();
        conf.setHost("localhost");
        conf.setPort(SERVER.getBkWebPort());
        configsImpl = new ConfigsImpl(new InnerHttpClient(conf));
    }

    @Test
    public void testPutConfig() throws BookkeeperAdminException {
        HashMap<String, String> config = new HashMap<>();
        config.put("allowEphemeralPorts", "false");
        configsImpl.putConfig(config);
        Map<String, String> resp = configsImpl.getConfig();
        Assertions.assertEquals("false", resp.get("allowEphemeralPorts"));
    }

    @Test
    public void testGetConfig() throws BookkeeperAdminException {
        Map<String, String> config = configsImpl.getConfig();
        Assertions.assertTrue(config.size() != 0);
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }
}
