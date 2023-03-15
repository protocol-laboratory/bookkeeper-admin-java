package io.github.protocol.bookkeeper;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookiesImpl implements Bookies {

    private final InnerHttpClient innerHttpClient;

    public BookiesImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }

    @Override
    public Map<String, String> bookieList() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_LIST);
            return JacksonService.toRefer(resp.body(), new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public Map<String, String> listBookieInfo() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_LIST_INFO);
            return JacksonService.toRefer(resp.body(), new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }

    }

    @Override
    public LastLogMark lastLogMark() throws BookkeeperAdminException {
        Map<String, String> map;
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_LAST_LOG_MARK);
            map = JacksonService.toRefer(resp.body(), new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
        LastLogMark lastLogMark = new LastLogMark();
        HashMap<Integer, Integer> lastLogMarks = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            lastLogMarks.put(lastLogMark.extractValueFromLastLogMarkKey(entry.getKey()),
                    lastLogMark.extractValueFromLastLogMarkValue(entry.getValue()));
        }
        lastLogMark.setLogFileIdTxnMap(lastLogMarks);
        return lastLogMark;
    }

    @Override
    public DiskFile listDiskFile() throws BookkeeperAdminException {
        Map<String, String> map;
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_LIST_DISK_FILE);
            map = JacksonService.toRefer(resp.body(), new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }

        DiskFile diskFile = new DiskFile();
        diskFile.setIndexFiles(map.get("index files").split("\t"));
        diskFile.setJournalFiles(map.get("journal files").split("\t"));
        diskFile.setEntryLogFiles(map.get("entrylog files").split("\t"));
        return diskFile;
    }

    @Override
    public void expandStorage() throws BookkeeperAdminException {
        try {
            innerHttpClient.put(UrlConst.BOOKIE_EXPAND_STORAGE);
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void forceGc(boolean forceMajor, boolean forceMinor) throws BookkeeperAdminException {
        HashMap<String, Boolean> requestBody = new HashMap<>();
        requestBody.put("forceMajor", forceMajor);
        requestBody.put("forceMinor", forceMinor);
        try {
            innerHttpClient.put(UrlConst.BOOKIE_GC, JacksonService.toJson(requestBody));
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public boolean isInForceGc() throws BookkeeperAdminException {
        Map<String, String> map;
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_GC);
            map = JacksonService.toRefer(resp.body(), new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
        return Boolean.parseBoolean(map.get("is_in_force_gc"));
    }

    @Override
    public void suspendGc(boolean major, boolean minor) throws BookkeeperAdminException {
        HashMap<String, Boolean> requestBody = new HashMap<>();
        requestBody.put("suspendMajor", major);
        requestBody.put("suspendMinor", minor);
        try {
            innerHttpClient.put(UrlConst.BOOKIE_GC_SUSPEND_COMPACTION, JacksonService.toJson(requestBody));
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public GcSuspendStatus gcSuspendStatus() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_GC_SUSPEND_COMPACTION);
            Map<String, Boolean> map = JacksonService.toRefer(resp.body(), new TypeReference<Map<String, Boolean>>() {
            });
            boolean isMajorGcSuspended = map.get("isMajorGcSuspended");
            boolean isMinorGcSuspended = map.get("isMinorGcSuspended");
            return new GcSuspendStatus(isMajorGcSuspended, isMinorGcSuspended);
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void resumeGc(boolean major, boolean minor) throws BookkeeperAdminException {
        HashMap<String, Boolean> requestBody = new HashMap<>();
        requestBody.put("resumeMajor", major);
        requestBody.put("resumeMinor", minor);
        try {
            innerHttpClient.put(UrlConst.BOOKIE_GC_RESUME_COMPACTION, JacksonService.toJson(requestBody));
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public List<GarbageCollectionStatus> gcStatusList() throws BookkeeperAdminException {
        List<Map<String, Object>> list;
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_GC_DETAILS);
            list = JacksonService.toRefer(resp.body(), new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
        return list.stream().map(map -> {
            GarbageCollectionStatus garbageCollectionStatus = new GarbageCollectionStatus();
            garbageCollectionStatus.setForceCompacting((boolean) map.get("forceCompacting"));
            garbageCollectionStatus.setMajorCompacting((boolean) map.get("majorCompacting"));
            garbageCollectionStatus.setMinorCompacting((boolean) map.get("minorCompacting"));
            garbageCollectionStatus.setLastMajorCompactionTime((long) map.get("lastMajorCompactionTime"));
            garbageCollectionStatus.setLastMinorCompactionTime((long) map.get("lastMinorCompactionTime"));
            garbageCollectionStatus.setMajorCompactionCounter((int) map.get("majorCompactionCounter"));
            garbageCollectionStatus.setMinorCompactionCounter((int) map.get("minorCompactionCounter"));
            return garbageCollectionStatus;
        }).collect(Collectors.toList());
    }

    @Override
    public BookieStatus status() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_STATE);
            return JacksonService.toObject(resp.body(), BookieStatus.class);
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) throws BookkeeperAdminException {
        HashMap<String, Boolean> requestBody = new HashMap<>();
        requestBody.put("readOnly", readOnly);
        try {
            innerHttpClient.put(UrlConst.BOOKIE_STATE_READ_ONLY, JacksonService.toJson(requestBody));
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public boolean isReadOnly() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_STATE_READ_ONLY);
            Map<String, Boolean> map = JacksonService.toRefer(resp.body(), new TypeReference<Map<String, Boolean>>() {
            });
            return map.get("readOnly");
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public boolean isReady() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_READY);
            return "OK".equals(resp.body());
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public BookieInfo bookieInfo() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.BOOKIE_INFO);
            return JacksonService.toObject(resp.body(), BookieInfo.class);
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }
}
