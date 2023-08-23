package com.tracer.logger.rest.services;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.mappers.TBLRestLogMapper;
import com.tracer.logger.rest.models.TBLRestLog;
import com.tracer.logger.rest.repos.TBLRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TBLRestService {

    private final TBLRestRepo tblRestRepo;
    private final DateService dateService;

    @Autowired
    public TBLRestService(TBLRestRepo tblRestRepo, DateService dateService) {
        this.tblRestRepo = tblRestRepo;
        this.dateService = dateService;
    }


    public TBLRestLog log(TBLRestLogDTO tblRestLogDTO) {
        TBLRestLog tblRestLog = TBLRestLogMapper.convertToEntity(tblRestLogDTO);

        return tblRestRepo.save(tblRestLog);
    }

    public List<TBLRestLog> findAll() {
        return tblRestRepo.findAll();
    }

    public Optional<TBLRestLog> findByUUID(String uuid) {
        return tblRestRepo.findById(uuid);
    }

    public List<TBLRestLog> findByDate(String start, String end) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateService.convertStringToDate(start);
            endDate = dateService.convertStringToDate(end);
        } catch (DateTimeException e) {
            throw new DateException(e.getMessage());
        }

        return tblRestRepo.findByDateInitBetween(startDate, endDate);
    }
}
