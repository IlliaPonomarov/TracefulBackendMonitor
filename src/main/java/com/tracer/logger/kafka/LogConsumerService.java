package com.tracer.logger.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogConsumerService {


    @KafkaListener(topics = "#{'${kafka.consumer.topics}'.split(',')}")
    public void consumeTest(ConsumerRecord<String, String> record) {
        System.out.println();
    }
}
