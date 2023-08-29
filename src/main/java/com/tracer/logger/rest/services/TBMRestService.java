package com.tracer.logger.rest.services;

import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ServiceDoesNotExistException;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.repos.TBMRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.*;

@Service
public class TBMRestService {

    private final TBMRestRepo tbmRestRepo;
    private final DateService dateService;

    @Autowired
    public TBMRestService(TBMRestRepo tbmRestRepo, DateService dateService) {
        this.tbmRestRepo = tbmRestRepo;
        this.dateService = dateService;
    }


    public TBMRestLog log(TBMRestLogDTO tbmRestLogDTO) {
        TBMRestLog newTbmRestLog = TBMRestLogMapper.convertToEntity(tbmRestLogDTO);

        String service = newTbmRestLog.getService();

        List<Optional<TBMRestLog>> lexistTbmRestLogOptional = List.of(tbmRestRepo.findByService(service).stream().findFirst());
        Optional<TBMRestLog> existTbmRestLogOptional = tbmRestRepo.findByService(service);

        if (existTbmRestLogOptional.isPresent()) {
            TBMRestLog existingTbmRestLog = existTbmRestLogOptional.get();
            existingTbmRestLog.getRequest().addAll(newTbmRestLog.getRequest());
            existingTbmRestLog.getResponse().addAll(newTbmRestLog.getResponse());

            newTbmRestLog = existingTbmRestLog;
        }

        return tbmRestRepo.save(newTbmRestLog);
    }

    public List<TBMRestLog> findAll() {
        return tbmRestRepo.findAll();
    }

    public Optional<TBMRestLog> findById(UUID uuid) {
        return tbmRestRepo.findById(uuid);
    }

    public List<TBMRestLog> findRestLogByBetweenDateAndService(String start, String end, String service) {

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateService.convertStringToDate(start);
            endDate = dateService.convertStringToDate(end);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return tbmRestRepo.findByDateInitBetweenAndService(startDate, endDate, service);
    }

    public Optional<TBMRestLog> findByService(String service) {
        return tbmRestRepo.findByService(service);
    }

    public TBMRestLog deleteByService(String service) {
        return tbmRestRepo.deleteByService(service);
    }

    public void deleteById(UUID id) {
        Optional<TBMRestLog> tbmRestLogOptional = tbmRestRepo.findById(id);

        tbmRestRepo.deleteById(id);
    }

    public void deleteAll() {
        tbmRestRepo.deleteAll();
    }
}
