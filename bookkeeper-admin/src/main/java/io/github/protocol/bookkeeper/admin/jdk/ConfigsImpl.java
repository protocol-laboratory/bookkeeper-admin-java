package io.github.protocol.bookkeeper.admin.jdk;


import io.github.openfacade.http.HttpResponse;
import io.github.protocol.bookkeeper.admin.common.JacksonService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ConfigsImpl implements Configs {

    private final InnerHttpClient innerHttpClient;

    public ConfigsImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }

    @Override
    public void putConfig(Map<String, String> config) throws BookkeeperAdminException {
        try {
            innerHttpClient.put(UrlConst.CONFIG_SERVER_CONFIG, config);
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public Map<String, String> getConfig() throws BookkeeperAdminException {
        try {
            HttpResponse response = innerHttpClient.get(UrlConst.CONFIG_SERVER_CONFIG);
            return JacksonService.toObject(response.body(), Map.class);
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }
}
