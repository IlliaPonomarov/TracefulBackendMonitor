package com.tracer.logger.rest.repos;

import com.tracer.logger.rest.models.RestLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TBMRestRepo extends MongoRepository<RestLog, UUID> {
    Optional<RestLog> findById(String id);
    List<RestLog> findByDateInitBetweenAndService(Date dateInit, Date dateEnd, String service);

    List<RestLog> findByDateInitAfterAndService(Date dateInit, String service);
    List<RestLog> findByDateInitBeforeAndService(Date dateInit, String service);

    Optional<List<RestLog>> findByService(String service);

    RestLog deleteByService(String service);

    void deleteById(String id);
}
