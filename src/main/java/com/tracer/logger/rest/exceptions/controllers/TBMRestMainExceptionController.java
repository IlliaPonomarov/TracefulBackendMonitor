package com.tracer.logger.rest.exceptions.controllers;

import com.tracer.logger.rest.exceptions.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ComponentScan(basePackages = "com.tracer.logger.rest.controllers")
public class TBMRestMainExceptionController {

    @ExceptionHandler(value = {TBMRestLogNotFounded.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage logNotFound(TBMRestLogNotFounded ex) {
        String errorMessage = ex.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();

        return new ErrorMessage(errorMessage, statusCode);
    }


    @ExceptionHandler(value = {TBMRestLogBadRequest.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage logBadRequest(TBMRestLogBadRequest ex) {
        String errorMessage = ex.getMessage();
        int statusCode = HttpStatus.BAD_REQUEST.value();

        return new ErrorMessage(errorMessage, statusCode);
    }

    @ExceptionHandler(value = {MappingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage logBadRequest(MappingException ex) {
        String errorMessage = ex.getMessage();
        int statusCode = HttpStatus.BAD_REQUEST.value();

        return new ErrorMessage(errorMessage, statusCode);
    }

    @ExceptionHandler(UUIDInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUUIDInvalidException(UUIDInvalidException e) {
        String errorMessage = e.getMessage();
        int statusCode = HttpStatus.BAD_REQUEST.value();

        return new ErrorMessage(errorMessage, statusCode);
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleServiceNotFoundException(ServiceNotFoundException e) {
        String errorMessage = e.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();

        return new ErrorMessage(errorMessage, statusCode);
    }


}
