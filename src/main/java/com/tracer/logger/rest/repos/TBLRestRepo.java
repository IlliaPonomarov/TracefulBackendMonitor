package com.tracer.logger.rest.repos;

import com.tracer.logger.rest.models.TBLRestLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TBLRestRepo extends MongoRepository<TBLRestLog, String> {
    Optional<TBLRestLog> findById(String id);
    List<TBLRestLog> findByDateInitBetween(Date dateInit, Date dateEnd);
}
