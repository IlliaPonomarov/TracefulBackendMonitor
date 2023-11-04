package com.tracer.logger.kafka.exceptions;

import com.tracer.logger.rest.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class KafkaControllerAdvice {

    @ExceptionHandler(KafkaLogsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleKafkaLogsNotFoundException(KafkaLogsNotFoundException ex) {
        String errorMessage = ex.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();

        return new ErrorMessage(errorMessage, statusCode);
    }
}
