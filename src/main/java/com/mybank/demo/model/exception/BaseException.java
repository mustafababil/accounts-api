package com.mybank.demo.model.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -8777654842213083386L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
