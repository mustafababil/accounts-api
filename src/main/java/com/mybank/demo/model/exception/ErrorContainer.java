package com.mybank.demo.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorContainer {
    private final List<Error> errors;
}