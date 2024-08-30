package io.github.protocol.bookkeeper.admin.reactor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Configuration {
    private String host = "localhost";

    private int port;

    public boolean tlsEnable;

    public Configuration() {
    }
}
