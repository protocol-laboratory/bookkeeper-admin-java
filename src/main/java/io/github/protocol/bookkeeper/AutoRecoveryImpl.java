package io.github.protocol.bookkeeper;

public class AutoRecoveryImpl implements AutoRecovery {
    private final InnerHttpClient innerHttpClient;

    public AutoRecoveryImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }
}
