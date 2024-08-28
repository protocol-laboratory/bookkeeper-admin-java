package io.github.protocol.bookkeeper;

public interface AutoRecovery {

    AutoRecoveryStatus autoRecoveryStatus() throws BookkeeperAdminException;

    void recoveryBookie(RecoveryBookieReqData reqData) throws BookkeeperAdminException;

    UnderReplicatedLedger listUnderReplicatedLedger(ListUnderReplicatedLedgerReqData reqData)
        throws BookkeeperAdminException;

    Auditor whoIsAuditor() throws BookkeeperAdminException;

    void triggerAudit() throws BookkeeperAdminException;

    void lostBookieRecoveryDelay() throws BookkeeperAdminException;

    void lostBookieRecoveryDelay(int delaySeconds) throws BookkeeperAdminException;

    void decommission(String bookieId) throws BookkeeperAdminException;

}
