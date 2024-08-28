package io.github.protocol.bookkeeper.admin.jdk;


import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConfigsImpl implements Configs {

    private final InnerHttpClient innerHttpClient;

    public ConfigsImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }

    @Override
    public void putConfig(Map<String, String> config) throws BookkeeperAdminException {
        try {
            innerHttpClient.put(UrlConst.CONFIG_SERVER_CONFIG, JacksonService.toJson(config));
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public Map<String, String> getConfig() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.CONFIG_SERVER_CONFIG);
            return JacksonService.toObject(resp.body(), Map.class);
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }
}
