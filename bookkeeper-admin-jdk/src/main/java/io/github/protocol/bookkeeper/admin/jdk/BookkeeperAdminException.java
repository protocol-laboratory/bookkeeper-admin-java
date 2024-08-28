package io.github.protocol.bookkeeper.admin.jdk;

public class BookkeeperAdminException extends Exception {
    private static final int DEFAULT_STATUS_CODE = 500;

    private final int statusCode;

    public BookkeeperAdminException(Throwable t) {
        super(t);
        statusCode = DEFAULT_STATUS_CODE;
    }

    public BookkeeperAdminException(String message) {
        this(message, DEFAULT_STATUS_CODE);
    }

    public BookkeeperAdminException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
