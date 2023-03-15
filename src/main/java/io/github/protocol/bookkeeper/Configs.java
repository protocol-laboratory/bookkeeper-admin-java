package io.github.protocol.bookkeeper;


import java.util.Map;

public interface Configs {

    void putConfig(Map<String, String> config) throws BookkeeperAdminException;

    Map<String, String> getConfig() throws BookkeeperAdminException;

}
