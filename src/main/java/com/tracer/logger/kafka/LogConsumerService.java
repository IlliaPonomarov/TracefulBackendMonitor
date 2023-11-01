package com.tracer.logger.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogConsumerService {


    private List<String> topics;

    @Value("#{'${kafka.consumer.topics}'.split(',')}")
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
    public List<String> getTopics() {
        return topics;
    }

    @KafkaListener(topics = "topic.log.tbm")
    public void consumeTest(String message) {
        System.out.println("Consumed message: " + message);
    }
}
