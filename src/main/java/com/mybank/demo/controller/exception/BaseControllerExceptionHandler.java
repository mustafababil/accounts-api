package com.mybank.demo.controller.exception;

import com.mybank.demo.model.exception.Error;
import com.mybank.demo.model.exception.ErrorContainer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new Error(fieldName, errorMessage));
        });

        ErrorContainer errorContainer = new ErrorContainer(errors);
        return new ResponseEntity<>(errorContainer, HttpStatus.BAD_REQUEST);
    }



    protected ResponseEntity<Object> buildErrorResponse(HttpStatus httpStatus, List<String> messages) {
        List<Error> errors = messages.stream().map(Error::new).collect(Collectors.toList());

        ErrorContainer errorContainer = new ErrorContainer(errors);

        return new ResponseEntity<>(errorContainer, httpStatus);
    }
}