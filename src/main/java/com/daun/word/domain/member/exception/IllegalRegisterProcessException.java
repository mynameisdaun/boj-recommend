package com.daun.word.domain.member.exception;

public class IllegalRegisterProcessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalRegisterProcessException() {
        super();
    }

    public IllegalRegisterProcessException(String message) {
        super(message);
    }

    public IllegalRegisterProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRegisterProcessException(Throwable cause) {
        super(cause);
    }

    protected IllegalRegisterProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
