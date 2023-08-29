package com.tracer.logger.rest.exceptions;

public class ServiceDoesNotExistException extends RuntimeException{
    public ServiceDoesNotExistException(String s) {
        super(s);
    }
}
