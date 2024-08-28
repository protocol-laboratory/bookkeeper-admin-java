package io.github.protocol.bookkeeper.admin.jdk;

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
        this.conf.setHost(host);
        return this;
    }

    @Override
    public BookkeeperAdminBuilder port(int port) {
        this.conf.setPort(port);
        return this;
    }
}
