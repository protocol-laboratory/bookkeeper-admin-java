package io.github.protocol.bookkeeper.admin.jdk;

public class UrlConst {
    public static final String HEARTBEAT_URL = "/heartbeat";

    public static final String PATH = "/api/v1";
    public static final String CONFIG = PATH + "/config";
    public static final String LEDGER = PATH + "/ledger";
    public static final String BOOKIE = PATH + "/bookie";
    public static final String AUTO_RECOVERY = PATH + "/autorecovery";

    public static final String CONFIG_SERVER_CONFIG = CONFIG + "/server_config";

    public static final String LEDGER_DELETE = LEDGER + "/delete";
    public static final String LEDGER_LIST = LEDGER + "/list";
    public static final String LEDGER_METADATA = LEDGER + "/metadata";
    public static final String LEDGER_READ = LEDGER + "/read";

    public static final String BOOKIE_LIST = BOOKIE + "/list_bookies";
    public static final String BOOKIE_LIST_INFO = BOOKIE + "/list_bookie_info";
    public static final String BOOKIE_LAST_LOG_MARK = BOOKIE + "/last_log_mark";
    public static final String BOOKIE_LIST_DISK_FILE = BOOKIE + "/list_disk_file";
    public static final String BOOKIE_EXPAND_STORAGE = BOOKIE + "/expand_storage";
    public static final String BOOKIE_GC = BOOKIE + "/gc";
    public static final String BOOKIE_GC_SUSPEND_COMPACTION = BOOKIE + "/gc/gc_suspend_compaction";
    public static final String BOOKIE_GC_RESUME_COMPACTION = BOOKIE + "/gc/gc_resume_compaction";
    public static final String BOOKIE_GC_DETAILS = BOOKIE + "/gc_details";
    public static final String BOOKIE_STATE = BOOKIE + "/state";
    public static final String BOOKIE_SANITY = BOOKIE + "/sanity";
    public static final String BOOKIE_STATE_READ_ONLY = BOOKIE + "/state/readonly";
    public static final String BOOKIE_READY = BOOKIE + "/is_ready";
    public static final String BOOKIE_INFO = BOOKIE + "/info";
    public static final String BOOKIE_CLUSTER_INFO = BOOKIE + "/cluster_info";

    public static final String AUTO_RECOVERY_STATUS = AUTO_RECOVERY + "/status";
    public static final String AUTO_RECOVERY_BOOKIE = AUTO_RECOVERY + "/bookie";
    public static final String AUTO_RECOVERY_LIST_UNDER_REPLICATED_LEDGER = AUTO_RECOVERY
            + "/list_under_replicated_ledger";
    public static final String AUTO_RECOVERY_WHO_IS_AUDITOR = AUTO_RECOVERY + "/who_is_auditor";
    public static final String AUTO_RECOVERY_TRIGGER_AUDIT = AUTO_RECOVERY + "/trigger_audit";
    public static final String AUTO_RECOVERY_LOST_BOOKIE_RECOVERY_DELAY = AUTO_RECOVERY + "/lost_bookie_recovery_delay";
    public static final String AUTO_RECOVERY_DECOMMISSION = AUTO_RECOVERY + "/decommission";
}
