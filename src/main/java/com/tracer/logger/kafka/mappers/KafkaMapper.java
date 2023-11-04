package com.tracer.logger.kafka.mappers;

import com.tracer.logger.kafka.consumers.KafkaLogRecord;
import com.tracer.logger.kafka.dtos.KafkaLogRecordDTO;

public class KafkaMapper {

    public static KafkaLogRecordDTO convertToDTO(KafkaLogRecord record) {
        return new KafkaLogRecordDTO(
                record.getId(),
                record.getMessage(),
                record.getTopic(),
                record.getKey(),
                record.getValue(),
                record.getPartition(),
                record.getOffset(),
                record.getTimestamp(),
                record.getTimestampType(),
                record.getSerializedKeySize(),
                record.getSerializedValueSize(),
                record.getHeaders(),
                record.getLeaderEpoch()
        );
    }

    public static KafkaLogRecord convertToEntity(KafkaLogRecordDTO kafkaLogRecordDTO) {

        return new KafkaLogRecord(
                kafkaLogRecordDTO.getId(),
                kafkaLogRecordDTO.getMessage(),
                kafkaLogRecordDTO.getTopic(),
                kafkaLogRecordDTO.getKey(),
                kafkaLogRecordDTO.getValue(),
                kafkaLogRecordDTO.getPartition(),
                kafkaLogRecordDTO.getOffset(),
                kafkaLogRecordDTO.getTimestamp(),
                kafkaLogRecordDTO.getTimestampType(),
                kafkaLogRecordDTO.getSerializedKeySize(),
                kafkaLogRecordDTO.getSerializedValueSize(),
                kafkaLogRecordDTO.getHeaders(),
                kafkaLogRecordDTO.getLeaderEpoch()
        );
    }
}
