package io.github.protocol.bookkeeper.admin.jdk;

import io.github.embedded.bookkeeper.core.EmbeddedBookkeeperServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class BookiesImplTest {

    private static final EmbeddedBookkeeperServer SERVER = new EmbeddedBookkeeperServer();

    private static Bookies bookiesImpl = null;

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        Configuration conf = new Configuration();
        conf.setHost("localhost");
        conf.setPort(SERVER.getBkWebPort());
        bookiesImpl = new BookiesImpl(new InnerHttpClient(conf));
    }

    @Test
    public void testBookiesList() throws BookkeeperAdminException {
        Map<String, String> map = bookiesImpl.bookieList();
        Assertions.assertEquals(1, map.size());
        String key = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
        }
        Assertions.assertTrue(key.contains(String.valueOf(SERVER.getBkPort())));
    }

    @Test
    public void testListBookieInfo() throws BookkeeperAdminException {
        Map<String, String> info = bookiesImpl.listBookieInfo();
        Assertions.assertEquals(2, info.size());
    }

    @Test
    public void testLastLogMark() throws BookkeeperAdminException {
        LastLogMark lastLogMark = bookiesImpl.lastLogMark();
        Assertions.assertEquals(1, lastLogMark.getLogFileIdTxnMap().size());
    }

    @Test
    public void testListDiskFile() throws BookkeeperAdminException {
        bookiesImpl.listDiskFile();
    }

    @Test
    public void testIsInForceGc() throws BookkeeperAdminException {
        boolean inForceGc = bookiesImpl.isInForceGc();
        Assertions.assertFalse(inForceGc);
    }

    @Test
    public void testGcStatus() throws BookkeeperAdminException {
        List<GarbageCollectionStatus> statuses = bookiesImpl.gcStatusList();
        Assertions.assertEquals(1, statuses.size());
        GarbageCollectionStatus garbageCollectionStatus = statuses.get(0);
        Assertions.assertFalse(garbageCollectionStatus.isMajorCompacting());

    }

    @Test
    public void testBookiesStatus() throws BookkeeperAdminException {
        BookieStatus status = bookiesImpl.status();
        Assertions.assertTrue(status.isRunning());
        Assertions.assertFalse(status.isReadOnly());

    }

    @Test
    public void testIsReadOnly() throws BookkeeperAdminException {
        boolean readOnly = bookiesImpl.isReadOnly();
        Assertions.assertFalse(readOnly);
    }

    @Test
    public void testBookiesIsReady() throws BookkeeperAdminException {
        boolean ready = bookiesImpl.isReady();
        Assertions.assertTrue(ready);
    }

    @Test
    public void testBookiesInfo() throws BookkeeperAdminException {
        BookieInfo bookieInfo = bookiesImpl.bookieInfo();
        Assertions.assertTrue(bookieInfo.getFreeSpace() > 0);
        Assertions.assertTrue(bookieInfo.getTotalSpace() > 0);
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }

}
