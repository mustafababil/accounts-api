package com.mybank.demo.model.exception;

public class InvalidCurrencyFormatException extends BaseException {

    private static final long serialVersionUID = 7473817425839445009L;

    public InvalidCurrencyFormatException(String message) {
        super(message);
    }
}
