package com.daun.word.domain.member.exception;

public class DuplicateMemberException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateMemberException() {
        super();
    }

    public DuplicateMemberException(String message) {
        super(message);
    }

    public DuplicateMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateMemberException(Throwable cause) {
        super(cause);
    }

    protected DuplicateMemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
