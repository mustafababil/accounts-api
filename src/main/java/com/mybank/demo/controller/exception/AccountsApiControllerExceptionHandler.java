package com.mybank.demo.controller.exception;

import com.mybank.demo.model.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice(basePackages = "com.mybank.demo.controller")
public class AccountsApiControllerExceptionHandler extends BaseControllerExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public final ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return buildErrorResponse(status, List.of(ex.getMessage()));
    }
}
