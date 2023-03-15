package io.github.protocol.bookkeeper;

import java.util.List;
import java.util.Map;

public interface Bookies {

    Map<String, String> bookieList() throws BookkeeperAdminException;

    Map<String, String> listBookieInfo() throws BookkeeperAdminException;

    LastLogMark lastLogMark() throws BookkeeperAdminException;

    DiskFile listDiskFile() throws BookkeeperAdminException;

    void expandStorage() throws BookkeeperAdminException;

    void forceGc(boolean forceMajor, boolean forceMinor) throws BookkeeperAdminException;

    boolean isInForceGc() throws BookkeeperAdminException;

    void suspendGc(boolean major, boolean minor) throws BookkeeperAdminException;

    GcSuspendStatus gcSuspendStatus() throws BookkeeperAdminException;

    void resumeGc(boolean major, boolean minor) throws BookkeeperAdminException;

    List<GarbageCollectionStatus> gcStatusList() throws BookkeeperAdminException;

    BookieStatus status() throws BookkeeperAdminException;

    void setReadOnly(boolean readOnly) throws BookkeeperAdminException;

    boolean isReadOnly() throws BookkeeperAdminException;

    boolean isReady() throws BookkeeperAdminException;

    BookieInfo bookieInfo() throws BookkeeperAdminException;

}
