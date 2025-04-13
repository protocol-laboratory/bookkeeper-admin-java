package io.github.protocol.bookkeeper.admin.jdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class RecoveryBookieReqData {
    @JsonProperty("bookie_src")
    private List<String> bookieSrc;

    @JsonProperty("delete_cookie")
    private boolean deleteCookie;

    public void setBookieSrc(List<String> bookieSrc) {
        this.bookieSrc = bookieSrc;
    }

    public void setDeleteCookie(boolean deleteCookie) {
        this.deleteCookie = deleteCookie;
    }
}
