package com.tracer.logger.rest.controllers;

import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.services.TBMRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/date")
public class TBMRestDateController {

    private final TBMRestService TBMRestService;

    @Autowired
    public TBMRestDateController(TBMRestService TBMRestService) {
        this.TBMRestService = TBMRestService;
    }

    @GetMapping("/{start}/{end}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TBLRestLogDTO> findByDate(@PathVariable String start, @PathVariable String end) {

        List<TBMRestLog> TBMRestLog = null;
        try {
            TBMRestLog = TBMRestService.findByDate(start, end);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (TBMRestLog.isEmpty()) {
            throw new TBMRestLogNotFounded("Log not found");
        }

        return TBMRestLog.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }
}
