package io.github.protocol.bookkeeper.admin.jdk;

import io.github.embedded.bookkeeper.core.EmbeddedBookkeeperServer;
import io.github.protocol.bookkeeper.admin.api.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class AutoRecoveryImplTest {

    private static final EmbeddedBookkeeperServer SERVER = new EmbeddedBookkeeperServer();

    private static AutoRecoveryImpl autoRecovery = null;

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        Configuration conf = new Configuration();
        conf.host("localhost");
        conf.port(SERVER.getBkWebPort());
        autoRecovery = new AutoRecoveryImpl(new InnerHttpClient(conf));
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }

    @Test
    public void testAutoRecoveryStatus() throws BookkeeperAdminException {
        AutoRecoveryStatus status = autoRecovery.autoRecoveryStatus();
        Assertions.assertNotNull(status);
        Assertions.assertTrue(status.isEnabled());
    }

    @Test
    public void testRecoveryBookie() throws BookkeeperAdminException {
        RecoveryBookieReqData reqData = new RecoveryBookieReqData();
        reqData.setBookieSrc(Collections.singletonList("localhost:3181"));
        reqData.setDeleteCookie(false);
        autoRecovery.recoveryBookie(reqData);
    }

    @Test
    public void testRecoveryBookieError() {
        RecoveryBookieReqData reqData = new RecoveryBookieReqData();
        // bookie src is required
        reqData.setBookieSrc(null);
        reqData.setDeleteCookie(false);
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.recoveryBookie(reqData));
    }

    @Test
    public void testListUnderReplicatedLedger() {
        ListUnderReplicatedLedgerReqData reqData = new ListUnderReplicatedLedgerReqData();
        reqData.setPrintMissingReplica(true);
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.listUnderReplicatedLedger(reqData));
    }

    @Test
    public void testWhoIsAuditor() {
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.whoIsAuditor());
    }

    @Test
    public void testTriggerAudit() {
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.triggerAudit());
    }

    @Test
    public void testLostBookieRecoveryDelayByDefault() {
        Assertions.assertThrows(BookkeeperAdminException.class, () -> autoRecovery.lostBookieRecoveryDelay());
    }

    @Test
    public void testLostBookieRecoveryDelay() throws BookkeeperAdminException {
        autoRecovery.lostBookieRecoveryDelay(5);
    }

    @Test
    public void testDecommission() throws BookkeeperAdminException {
        autoRecovery.decommission("localhost:3181");
    }
}
