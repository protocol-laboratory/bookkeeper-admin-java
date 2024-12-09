package io.github.protocol.bookkeeper.admin.jdk;

public interface BookkeeperAdminBuilder {
    BookkeeperAdmin build();

    BookkeeperAdminBuilder host(String host);

    BookkeeperAdminBuilder port(int port);
}
