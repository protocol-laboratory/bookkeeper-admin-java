package io.github.protocol.bookkeeper;


import java.net.http.HttpResponse;
import java.util.Map;

public class ConfigsImpl implements Configs {

    private final InnerHttpClient innerHttpClient;

    public ConfigsImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }
    @Override
    public void putConfig(Map<String, String> config) throws Exception {
        innerHttpClient.put(UrlConst.CONFIG_SERVER_CONFIG, JacksonService.toJson(config));
    }

    @Override
    public Map<String, String> getConfig() throws Exception {
        HttpResponse<String> resp = innerHttpClient.get(UrlConst.CONFIG_SERVER_CONFIG);
        return JacksonService.toObject(resp.body(), Map.class);
    }
}
