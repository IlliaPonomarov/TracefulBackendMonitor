package com.tracer.logger.kafka.repos;

import com.tracer.logger.kafka.consumers.KafkaLogRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KafkaRepository extends MongoRepository<KafkaLogRecord, UUID> {

}
