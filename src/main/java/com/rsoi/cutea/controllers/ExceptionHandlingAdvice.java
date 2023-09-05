package com.rsoi.cutea.controllers;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionHandlingAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler() {
        return "Resource not found";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String runtimeExceptionHandler() {
        return "Runtime error";
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String forbiddenExceptionHandler() {
        return "This request is forbidden";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String otherExceptionHandler() {
        return "Unexpected error...";
    }
}