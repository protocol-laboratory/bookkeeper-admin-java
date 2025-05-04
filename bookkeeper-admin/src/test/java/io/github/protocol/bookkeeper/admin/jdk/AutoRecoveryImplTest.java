package io.github.protocol.bookkeeper.admin.jdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;

public class AutoRecoveryImplTest extends BaseTest {
    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testAutoRecoveryStatus(AutoRecoveryImpl autoRecovery) throws BookkeeperAdminException {
        AutoRecoveryStatus status = autoRecovery.autoRecoveryStatus();
        Assertions.assertNotNull(status);
        Assertions.assertTrue(status.isEnabled());
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testRecoveryBookie(AutoRecoveryImpl autoRecovery) throws BookkeeperAdminException {
        RecoveryBookieReqData reqData = new RecoveryBookieReqData();
        reqData.setBookieSrc(Collections.singletonList("localhost:3181"));
        reqData.setDeleteCookie(false);
        autoRecovery.recoveryBookie(reqData);
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testRecoveryBookieError(AutoRecoveryImpl autoRecovery) {
        RecoveryBookieReqData reqData = new RecoveryBookieReqData();
        // bookie src is required
        reqData.setBookieSrc(null);
        reqData.setDeleteCookie(false);
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.recoveryBookie(reqData));
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testListUnderReplicatedLedger(AutoRecoveryImpl autoRecovery) {
        ListUnderReplicatedLedgerReqData reqData = new ListUnderReplicatedLedgerReqData();
        reqData.setPrintMissingReplica(true);
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.listUnderReplicatedLedger(reqData));
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testWhoIsAuditor(AutoRecoveryImpl autoRecovery) {
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.whoIsAuditor());
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testTriggerAudit(AutoRecoveryImpl autoRecovery) {
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.triggerAudit());
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testLostBookieRecoveryDelayByDefault(AutoRecoveryImpl autoRecovery) {
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.lostBookieRecoveryDelay());
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testLostBookieRecoveryDelay(AutoRecoveryImpl autoRecovery) throws BookkeeperAdminException {
        autoRecovery.lostBookieRecoveryDelay(5);
    }

    @ParameterizedTest
    @MethodSource("provideAutoRecoveries")
    public void testDecommission(AutoRecoveryImpl autoRecovery) throws BookkeeperAdminException {
        autoRecovery.decommission("localhost:3181");
    }
}
