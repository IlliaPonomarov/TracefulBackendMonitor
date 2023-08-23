package com.tracer.logger.rest.exceptions.controllers;

import com.tracer.logger.rest.exceptions.ErrorMessage;
import com.tracer.logger.rest.exceptions.TBLRestLogBadRequest;
import com.tracer.logger.rest.exceptions.TBLRestLogNotFounded;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TBLRestControllerException {

    @ExceptionHandler(value = {TBLRestLogNotFounded.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage logNotFound(TBLRestLogNotFounded ex) {

        return new ErrorMessage(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    }


    @ExceptionHandler(value = {TBLRestLogBadRequest.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage logBadRequest(TBLRestLogBadRequest ex) {

        return new ErrorMessage(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
    }


}
