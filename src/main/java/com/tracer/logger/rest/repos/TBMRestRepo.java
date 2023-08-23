package com.tracer.logger.rest.repos;

import com.tracer.logger.rest.models.TBMRestLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TBMRestRepo extends MongoRepository<TBMRestLog, String> {
    Optional<TBMRestLog> findById(String id);
    List<TBMRestLog> findByDateInitBetween(Date dateInit, Date dateEnd);
}
