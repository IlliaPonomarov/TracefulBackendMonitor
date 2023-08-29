package com.tracer.logger.rest.repos;

import com.tracer.logger.rest.models.TBMRestLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TBMRestRepo extends MongoRepository<TBMRestLog, UUID> {
    Optional<TBMRestLog> findById(String id);
    List<TBMRestLog> findByDateInitBetweenAndService(Date dateInit, Date dateEnd, String service);

    Optional<TBMRestLog> findByService(String service);

    TBMRestLog deleteByService(String service);

    void deleteById(String id);
}
