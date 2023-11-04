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
}
