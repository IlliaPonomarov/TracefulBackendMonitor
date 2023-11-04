package com.tracer.logger.kafka.exceptions;

public class KafkaLogsNotFoundException extends RuntimeException{

    public KafkaLogsNotFoundException(String message) {
        super(message);
    }
}
