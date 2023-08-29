package com.tracer.logger.rest.exceptions.controllers;

import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ErrorMessage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ComponentScan(basePackages = "com.tracer.logger.rest.controllers")
public class TBMRestDateExceptionController {

    @ExceptionHandler(DateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDateException(DateException ex) {
        String errorMessage = ex.getMessage();
        int statusCode = HttpStatus.BAD_REQUEST.value();

        return new ErrorMessage(errorMessage, statusCode);
    }

}
