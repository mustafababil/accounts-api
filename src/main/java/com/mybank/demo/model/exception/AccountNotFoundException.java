package com.mybank.demo.model.exception;

public class AccountNotFoundException extends BaseException {

    private static final long serialVersionUID = -5790525449542815814L;

    public AccountNotFoundException(String message) {
        super(message);
    }
}
