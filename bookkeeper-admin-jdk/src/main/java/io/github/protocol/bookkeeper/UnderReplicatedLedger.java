package io.github.protocol.bookkeeper;

import java.util.List;

public class UnderReplicatedLedger {
    private List<Long> ledgers;

    public List<Long> getLedgers() {
        return ledgers;
    }

    public void setLedgers(List<Long> ledgers) {
        this.ledgers = ledgers;
    }
}
