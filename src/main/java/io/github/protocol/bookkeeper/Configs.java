package io.github.protocol.bookkeeper;


import java.util.Map;

public interface Configs {

    void putConfig(Map<String, String> config) throws Exception;

    Map<String, String> getConfig() throws Exception;

}
