package io.github.protocol.bookkeeper.admin.reactor;

import reactor.netty.http.client.HttpClient;

public class InnerReactorClient {
    private final HttpClient httpClient;

    private final String httpPrefix;

    public InnerReactorClient(Configuration conf) {
        HttpClient client = HttpClient.create();

        if (conf.isTlsEnable()) {
            this.httpPrefix = "https://" + conf.getHost() + ":" + conf.getPort();
        } else {
            this.httpPrefix = "http://" + conf.getHost() + ":" + conf.getPort();
        }

        this.httpClient = client;
    }
}
