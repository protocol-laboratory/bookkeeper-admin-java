package io.github.protocol.bookkeeper;

public interface BookkeeperAdminBuilder {
    BookkeeperAdmin build();

    BookkeeperAdminBuilder host(String host);

    BookkeeperAdminBuilder port(int port);
}
