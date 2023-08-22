package com.tracer.logger.rest.repos;

import com.tracer.logger.rest.models.TBLRestLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBLRestRepo extends MongoRepository<TBLRestLog, String> {
}
