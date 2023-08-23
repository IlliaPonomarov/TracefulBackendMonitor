package com.tracer.logger.rest.exceptions.controllers;

import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ErrorMessage;
import com.tracer.logger.rest.exceptions.TBMRestLogBadRequest;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ComponentScan(basePackages = "com.tracer.logger.rest.controllers")
public class TBMRestControllerException {

    @ExceptionHandler(value = {TBMRestLogNotFounded.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage logNotFound(TBMRestLogNotFounded ex) {

        return new ErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    }


    @ExceptionHandler(value = {TBMRestLogBadRequest.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage logBadRequest(TBMRestLogBadRequest ex) {

        return new ErrorMessage(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    @ExceptionHandler(DateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleDateException(DateException e) {

        return new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }


}
