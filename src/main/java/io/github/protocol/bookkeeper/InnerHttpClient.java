package io.github.protocol.bookkeeper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InnerHttpClient {
    private final Configuration conf;

    private final HttpClient client;

    private final String httpPrefix;

    public InnerHttpClient(Configuration conf) {
        this.conf = conf;
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        this.httpPrefix = "http://" + conf.getHost() + ":" + conf.getPort();
    }

    public HttpResponse<String> get(String urlSuffix) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.httpPrefix + urlSuffix))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
