package io.github.protocol.bookkeeper;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class AutoRecoveryImpl implements AutoRecovery {
    private final InnerHttpClient innerHttpClient;

    public AutoRecoveryImpl(InnerHttpClient innerHttpClient) {
        this.innerHttpClient = innerHttpClient;
    }

    @Override
    public AutoRecoveryStatus autoRecoveryStatus() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.AUTO_RECOVERY_STATUS);
            return JacksonService.toRefer(resp.body(), new TypeReference<AutoRecoveryStatus>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void recoveryBookie(RecoveryBookieReqData reqData) throws BookkeeperAdminException {
        try {
            innerHttpClient.put(UrlConst.AUTO_RECOVERY, reqData);
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public UnderReplicatedLedger listUnderReplicatedLedger(ListUnderReplicatedLedgerReqData reqData)
            throws BookkeeperAdminException {
        try {
            String url = UrlConst.AUTO_RECOVERY_LIST_UNDER_REPLICATED_LEDGER;
            if (reqData.isPrintMissingReplica()) {
                url += "?&printmissingreplica=true";
            } else {
                url += "?&printmissingreplica=false";
            }
            if (reqData.getExcludingBookieId() != null && !"".equals(reqData.getExcludingBookieId())) {
                url = url + "&excludingmissingreplica=" + reqData.getExcludingBookieId();
            }
            if (reqData.getIncludingBookieId() != null && !"".equals(reqData.getIncludingBookieId())) {
                url = url + "&missingreplica=" + reqData.getIncludingBookieId();
            }
            HttpResponse<String> resp = innerHttpClient.get(url);
            if ((resp.statusCode() < 200 || resp.statusCode() >= 300) && resp.body() != null) {
                throw new BookkeeperAdminException(resp.body());
            }
            return JacksonService.toRefer(resp.body(), new TypeReference<UnderReplicatedLedger>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public Auditor whoIsAuditor() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.get(UrlConst.AUTO_RECOVERY_WHO_IS_AUDITOR);

            if ((resp.statusCode() < 200 || resp.statusCode() >= 300) && resp.body() != null) {
                throw new BookkeeperAdminException(resp.body());
            }
            return JacksonService.toRefer(resp.body(), new TypeReference<Auditor>() {
            });
        } catch (Exception e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void triggerAudit() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.put(UrlConst.AUTO_RECOVERY_TRIGGER_AUDIT);
            if ((resp.statusCode() < 200 || resp.statusCode() >= 300) && resp.body() != null) {
                throw new BookkeeperAdminException(resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void lostBookieRecoveryDelay() throws BookkeeperAdminException {
        try {
            HttpResponse<String> resp = innerHttpClient.put(UrlConst.AUTO_RECOVERY_LOST_BOOKIE_RECOVERY_DELAY);
            if ((resp.statusCode() < 200 || resp.statusCode() >= 300) && resp.body() != null) {
                throw new BookkeeperAdminException(resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void lostBookieRecoveryDelay(int delaySeconds) throws BookkeeperAdminException {
        Map<String, Integer> reqDate = new HashMap<>();
        reqDate.put("delay_seconds", delaySeconds);
        try {
            HttpResponse<String> resp = innerHttpClient.put(UrlConst.AUTO_RECOVERY_LOST_BOOKIE_RECOVERY_DELAY, reqDate);
            if ((resp.statusCode() < 200 || resp.statusCode() >= 300) && resp.body() != null) {
                throw new BookkeeperAdminException(resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }

    @Override
    public void decommission(String bookieId) throws BookkeeperAdminException {
        if (bookieId == null || "".equals(bookieId)) {
            throw new BookkeeperAdminException("bookie is empty");
        }
        Map<String, String> reqDate = new HashMap<>();
        reqDate.put("bookie_src", bookieId);
        try {
            HttpResponse<String> resp = innerHttpClient.put(UrlConst.AUTO_RECOVERY_DECOMMISSION, reqDate);
            if ((resp.statusCode() < 200 || resp.statusCode() >= 300) && resp.body() != null) {
                throw new BookkeeperAdminException(resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new BookkeeperAdminException(e);
        }
    }
}
