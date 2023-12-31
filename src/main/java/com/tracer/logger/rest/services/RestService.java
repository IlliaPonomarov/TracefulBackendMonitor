package com.tracer.logger.rest.services;

import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ServiceDoesNotExistException;
import com.tracer.logger.rest.mappers.RestLogMapper;
import com.tracer.logger.rest.models.RestLog;
import com.tracer.logger.rest.repos.RestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.*;

@Service
public class RestService {

    private final RestRepo restRepo;
    private final DateService dateService;

    @Autowired  
    public RestService(RestRepo restRepo, DateService dateService) {
        this.restRepo = restRepo;
        this.dateService = dateService;
    }


    public RestLog log(RestLogDTO restLogDTO) {
        RestLog newRestLog = null;

        try {
            newRestLog = RestLogMapper.convertToEntity(restLogDTO);
        }
        catch (Exception e) {
            throw new MappingException(e.getMessage());
        }
        return restRepo.save(newRestLog);
    }

    public List<RestLog> findAll() {
        return restRepo.findAll();
    }

    public Optional<RestLog> findById(String uuid) {
        return restRepo.findById(uuid);
    }

    public List<RestLog> findByBetweenDateAndService(String start, String end, String service) {

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = dateService.convertStringToDate(start);
            endDate = dateService.convertStringToDate(end);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return restRepo.findByDateInitBetweenAndService(startDate, endDate, service);
    }

    public Optional<List<RestLog>> findByService(String service) {
        return restRepo.findByService(service);
    }

    public RestLog deleteByService(String service) {
        return restRepo.deleteByService(service);
    }

    public List<RestLog> deleteByService(List<RestLog> restLogs) {
         restLogs.forEach(
                restLog -> restRepo.deleteByService(restLog.getService())
        );

         return restLogs;
    }

    public void deleteById(String id) {
        Optional<RestLog> tbmRestLogOptional = restRepo.findById(id);

        if (tbmRestLogOptional.isEmpty()) {
            throw new ServiceDoesNotExistException("Service with id: " + id + " does not exist");
        }

        restRepo.deleteById(id);
    }

    public void deleteAll() {
        restRepo.deleteAll();
    }

    public List<RestLog> findByStartDateAndService(String start, String service) {
        Date startDate = null;
        try {
            startDate = dateService.convertStringToDate(start);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return restRepo.findByDateInitAfterAndService(startDate, service);
    }

    public List<RestLog> findByEndDateAndService(String end, String service) {
        Date endDate = null;
        try {
            endDate = dateService.convertStringToDate(end);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return restRepo.findByDateInitBeforeAndService(endDate, service);
    }
}
