package com.tracer.logger.kafka.services;

import com.tracer.logger.kafka.consumers.KafkaLogRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.tracer.logger.kafka.repos.KafkaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class KafkaLogConsumerService {


    private final KafkaRepository kafkaRepository;

    @Autowired
    public KafkaLogConsumerService(KafkaRepository kafkaRepository) {
        this.kafkaRepository = kafkaRepository;
    }

    @KafkaListener(topics = "#{'${kafka.consumer.topics}'.split(',')}")
    public void consumeTest(ConsumerRecord<String, String> record) {
        KafkaLogRecord kafkaLogRecord = new KafkaLogRecord(record);
        System.out.println(kafkaLogRecord.toString());

        kafkaLogRecord.setCreatedAt(new Date());
        kafkaLogRecord.setUpdatedAt(new Date());

        kafkaRepository.save(kafkaLogRecord);
    }

    public List<KafkaLogRecord> findAll() {
        return kafkaRepository.findAll();
    }
}
