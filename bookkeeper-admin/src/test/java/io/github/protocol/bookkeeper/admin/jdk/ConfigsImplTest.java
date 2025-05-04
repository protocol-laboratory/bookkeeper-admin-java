package io.github.protocol.bookkeeper.admin.jdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

public class ConfigsImplTest extends BaseTest {
    @ParameterizedTest
    @MethodSource("provideConfigsImpl")
    public void testPutConfig(Configs configsImpl) throws BookkeeperAdminException {
        HashMap<String, String> config = new HashMap<>();
        config.put("allowEphemeralPorts", "false");
        configsImpl.putConfig(config);
        Map<String, String> resp = configsImpl.getConfig();
        Assertions.assertEquals("false", resp.get("allowEphemeralPorts"));
    }

    @ParameterizedTest
    @MethodSource("provideConfigsImpl")
    public void testGetConfig(Configs configsImpl) throws BookkeeperAdminException {
        Map<String, String> config = configsImpl.getConfig();
        Assertions.assertTrue(config.size() != 0);
    }
}
