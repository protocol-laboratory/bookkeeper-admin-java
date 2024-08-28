package io.github.protocol.bookkeeper.admin.jdk;

import java.util.List;

public class RecoveryBookieReqData {
    private List<String> bookieSrc;
    private boolean deleteCookie;

    public List<String> getBookieSrc() {
        return bookieSrc;
    }

    public void setBookieSrc(List<String> bookieSrc) {
        this.bookieSrc = bookieSrc;
    }

    public boolean isDeleteCookie() {
        return deleteCookie;
    }

    public void setDeleteCookie(boolean deleteCookie) {
        this.deleteCookie = deleteCookie;
    }
}
