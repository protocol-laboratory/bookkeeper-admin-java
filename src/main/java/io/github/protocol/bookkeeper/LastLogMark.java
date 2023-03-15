package io.github.protocol.bookkeeper;

import java.util.Arrays;
import java.util.Map;

public class LastLogMark {

    private Map<Integer, Integer> logFileIdTxnMap;

    public Map<Integer, Integer> getLogFileIdTxnMap() {
        return logFileIdTxnMap;
    }

    public void setLogFileIdTxnMap(Map<Integer, Integer> logFileIdTxnMap) {
        this.logFileIdTxnMap = logFileIdTxnMap;
    }
    public int extractValueFromLastLogMarkKey(String data) {
        String result = Arrays.stream(data.replaceAll("LastLogMark: Journal Id -", "")
                .replaceAll(" ", "")
                .split("\\(")).findFirst().get();
        return Integer.parseInt(result);
    }

    public int extractValueFromLastLogMarkValue(String data) {
        String result = data.replace("Pos - ", "")
                .replaceAll(" ", "");
        return Integer.parseInt(result);
    }

}
