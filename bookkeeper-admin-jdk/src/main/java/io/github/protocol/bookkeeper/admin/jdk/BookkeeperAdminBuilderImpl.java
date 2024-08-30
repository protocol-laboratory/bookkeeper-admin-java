package io.github.protocol.bookkeeper.admin.jdk;

import io.github.protocol.bookkeeper.admin.api.Configuration;

public class BookkeeperAdminBuilderImpl implements BookkeeperAdminBuilder {
    private final Configuration conf;

    public BookkeeperAdminBuilderImpl() {
        this.conf = new Configuration();
    }

    @Override
    public BookkeeperAdmin build() {
        return new BookkeeperAdminImpl(conf);
    }

    @Override
    public BookkeeperAdminBuilder host(String host) {
        this.conf.host(host);
        return this;
    }

    @Override
    public BookkeeperAdminBuilder port(int port) {
        this.conf.port(port);
        return this;
    }
}
