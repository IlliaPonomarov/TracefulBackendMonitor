package com.tracer.logger.kafka.exceptions;

import com.tracer.logger.rest.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class KafkaControllerAdvice {

    @ExceptionHandler(KafkaLogsNotFoundException.class)
    public ErrorMessage handleKafkaLogsNotFoundException(KafkaLogsNotFoundException ex) {
        String errorMessage = ex.getMessage();
        int statusCode = HttpStatus.NOT_FOUND.value();

        return new ErrorMessage(errorMessage, statusCode);
    }
}
