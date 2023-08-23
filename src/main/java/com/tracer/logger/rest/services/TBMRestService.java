package com.tracer.logger.rest.services;

import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.repos.TBMRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TBMRestService {

    private final TBMRestRepo TBMRestRepo;
    private final DateService dateService;

    @Autowired
    public TBMRestService(TBMRestRepo TBMRestRepo, DateService dateService) {
        this.TBMRestRepo = TBMRestRepo;
        this.dateService = dateService;
    }


    public TBMRestLog log(TBLRestLogDTO tblRestLogDTO) {
        TBMRestLog TBMRestLog = TBMRestLogMapper.convertToEntity(tblRestLogDTO);

        return TBMRestRepo.save(TBMRestLog);
    }

    public List<TBMRestLog> findAll() {
        return TBMRestRepo.findAll();
    }

    public Optional<TBMRestLog> findByUUID(String uuid) {
        return TBMRestRepo.findById(uuid);
    }

    public List<TBMRestLog> findByDate(String start, String end) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateService.convertStringToDate(start);
            endDate = dateService.convertStringToDate(end);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return TBMRestRepo.findByDateInitBetween(startDate, endDate);
    }
}
