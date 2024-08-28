package io.github.protocol.bookkeeper.admin.jdk;

import java.net.http.HttpResponse;

public class HeartbeatImpl implements Heartbeat {
    private final InnerHttpClient innerHttpClient;

    public HeartbeatImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }

    @Override
    public void heartbeat() throws BookkeeperAdminException {
        String url = UrlConst.HEARTBEAT_URL;
        try {
            HttpResponse<String> httpResponse = innerHttpClient.get(url);
            if (httpResponse.statusCode() != 200) {
                throw new BookkeeperAdminException("healthcheck failed, status code: " + httpResponse.statusCode(),
                        httpResponse.statusCode());
            }
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }
}
