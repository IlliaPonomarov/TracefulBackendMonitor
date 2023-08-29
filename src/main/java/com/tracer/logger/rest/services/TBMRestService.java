package com.tracer.logger.rest.services;

import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ServiceDoesNotExistException;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.repos.TBMRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.MappingException;
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
        TBMRestLog newTbmRestLog = null;

        try {
            newTbmRestLog = TBMRestLogMapper.convertToEntity(tbmRestLogDTO);
        }
        catch (Exception e) {
            throw new MappingException(e.getMessage());
        }
        return tbmRestRepo.save(newTbmRestLog);
    }

    public List<TBMRestLog> findAll() {
        return tbmRestRepo.findAll();
    }

    public Optional<TBMRestLog> findById(String uuid) {
        return tbmRestRepo.findById(uuid);
    }

    public List<TBMRestLog> findByBetweenDateAndService(String start, String end, String service) {

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

    public Optional<List<TBMRestLog>> findByService(String service) {
        return tbmRestRepo.findByService(service);
    }

    public TBMRestLog deleteByService(String service) {
        return tbmRestRepo.deleteByService(service);
    }

    public void deleteById(String id) {
        Optional<TBMRestLog> tbmRestLogOptional = tbmRestRepo.findById(id);

        if (tbmRestLogOptional.isEmpty()) {
            throw new ServiceDoesNotExistException("Service with id: " + id + " does not exist");
        }

        tbmRestRepo.deleteById(id);
    }

    public void deleteAll() {
        tbmRestRepo.deleteAll();
    }

    public List<TBMRestLog> findByStartDateAndService(String start, String service) {
        Date startDate = null;
        try {
            startDate = dateService.convertStringToDate(start);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return tbmRestRepo.findByDateInitAfterAndService(startDate, service);
    }

    public List<TBMRestLog> findByEndDateAndService(String end, String service) {
        Date endDate = null;
        try {
            endDate = dateService.convertStringToDate(end);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return tbmRestRepo.findByDateInitBeforeAndService(endDate, service);
    }
}
