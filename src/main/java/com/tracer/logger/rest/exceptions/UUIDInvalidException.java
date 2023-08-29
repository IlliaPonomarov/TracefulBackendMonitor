package com.tracer.logger.rest.exceptions;

public class UUIDInvalidException extends RuntimeException {
    public UUIDInvalidException(String format) {
        super(format);
    }
}
