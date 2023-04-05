package io.github.protocol.bookkeeper;

public class ListUnderReplicatedLedgerReqData {
    private String includingBookieId;
    private String excludingBookieId;
    private boolean printMissingReplica;

    public String getIncludingBookieId() {
        return includingBookieId;
    }

    public void setIncludingBookieId(String includingBookieId) {
        this.includingBookieId = includingBookieId;
    }

    public String getExcludingBookieId() {
        return excludingBookieId;
    }

    public void setExcludingBookieId(String excludingBookieId) {
        this.excludingBookieId = excludingBookieId;
    }

    public boolean isPrintMissingReplica() {
        return printMissingReplica;
    }

    public void setPrintMissingReplica(boolean printMissingReplica) {
        this.printMissingReplica = printMissingReplica;
    }
}
