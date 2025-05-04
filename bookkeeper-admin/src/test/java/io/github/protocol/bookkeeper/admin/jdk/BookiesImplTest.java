package io.github.protocol.bookkeeper.admin.jdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class BookiesImplTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testBookiesList(Bookies bookiesImpl) throws BookkeeperAdminException {
        Map<String, String> map = bookiesImpl.bookieList();
        Assertions.assertEquals(1, map.size());
        String key = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
        }
        Assertions.assertTrue(key.contains(String.valueOf(server.getBkPort())));
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testListBookieInfo(Bookies bookiesImpl) throws BookkeeperAdminException {
        Map<String, String> info = bookiesImpl.listBookieInfo();
        Assertions.assertEquals(2, info.size());
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testLastLogMark(Bookies bookiesImpl) throws BookkeeperAdminException {
        LastLogMark lastLogMark = bookiesImpl.lastLogMark();
        Assertions.assertEquals(1, lastLogMark.getLogFileIdTxnMap().size());
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testListDiskFile(Bookies bookiesImpl) throws BookkeeperAdminException {
        bookiesImpl.listDiskFile();
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testIsInForceGc(Bookies bookiesImpl) throws BookkeeperAdminException {
        boolean inForceGc = bookiesImpl.isInForceGc();
        Assertions.assertFalse(inForceGc);
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testGcStatus(Bookies bookiesImpl) throws BookkeeperAdminException {
        List<GarbageCollectionStatus> statuses = bookiesImpl.gcStatusList();
        Assertions.assertEquals(1, statuses.size());
        GarbageCollectionStatus garbageCollectionStatus = statuses.get(0);
        Assertions.assertFalse(garbageCollectionStatus.isMajorCompacting());

    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testBookiesStatus(Bookies bookiesImpl) throws BookkeeperAdminException {
        BookieStatus status = bookiesImpl.status();
        Assertions.assertTrue(status.isRunning());
        Assertions.assertFalse(status.isReadOnly());

    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testIsReadOnly(Bookies bookiesImpl) throws BookkeeperAdminException {
        boolean readOnly = bookiesImpl.isReadOnly();
        Assertions.assertFalse(readOnly);
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testBookiesIsReady(Bookies bookiesImpl) throws BookkeeperAdminException {
        boolean ready = bookiesImpl.isReady();
        Assertions.assertTrue(ready);
    }

    @ParameterizedTest
    @MethodSource("provideBookiesImpl")
    public void testBookiesInfo(Bookies bookiesImpl) throws BookkeeperAdminException {
        BookieInfo bookieInfo = bookiesImpl.bookieInfo();
        Assertions.assertTrue(bookieInfo.getFreeSpace() > 0);
        Assertions.assertTrue(bookieInfo.getTotalSpace() > 0);
    }

}
