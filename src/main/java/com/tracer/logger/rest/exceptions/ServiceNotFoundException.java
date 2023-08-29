package com.tracer.logger.rest.exceptions;

public class ServiceNotFoundException extends RuntimeException{

        public ServiceNotFoundException(String message) {
            super(message);
        }
}
