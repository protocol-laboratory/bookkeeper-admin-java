package io.github.protocol.bookkeeper;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Configuration {
    private String host = "localhost";

    private int port;

    public Configuration() {
    }
}
