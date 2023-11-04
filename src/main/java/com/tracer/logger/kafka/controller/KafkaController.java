package com.tracer.logger.kafka.controller;

import com.tracer.logger.kafka.services.KafkaLogConsumerService;
import com.tracer.logger.kafka.consumers.KafkaLogRecord;
import com.tracer.logger.kafka.dtos.KafkaLogRecordDTO;
import com.tracer.logger.kafka.exceptions.KafkaLogsNotFoundException;
import com.tracer.logger.kafka.mappers.KafkaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    private final KafkaLogConsumerService kafkaLogConsumerService;

    @Autowired
    public KafkaController(KafkaLogConsumerService kafkaLogConsumerService) {
        this.kafkaLogConsumerService = kafkaLogConsumerService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<KafkaLogRecordDTO> getAll() {
        List<KafkaLogRecord> kafkaLogRecords = kafkaLogConsumerService.findAll();
        List<KafkaLogRecordDTO> kafkaLogRecordDTOS = Collections.emptyList();

        if (kafkaLogRecords.isEmpty())
            throw new KafkaLogsNotFoundException("No logs found in Kafka");

        kafkaLogRecordDTOS = kafkaLogRecords.stream().map(KafkaMapper::convertToDTO).toList();

        return kafkaLogRecordDTOS;
    }
}
