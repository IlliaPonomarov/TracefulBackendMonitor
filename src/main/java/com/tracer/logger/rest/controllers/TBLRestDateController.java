package com.tracer.logger.rest.controllers;

import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.TBLRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBLRestLogMapper;
import com.tracer.logger.rest.models.TBLRestLog;
import com.tracer.logger.rest.services.TBLRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/date")
public class TBLRestDateController {

    private final TBLRestService tblRestService;

    @Autowired
    public TBLRestDateController(TBLRestService tblRestService) {
        this.tblRestService = tblRestService;
    }

    @GetMapping("/{start}/{end}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TBLRestLogDTO> findByDate(@PathVariable String start, @PathVariable String end) {

        List<TBLRestLog> tblRestLog = null;
        try {
            tblRestLog = tblRestService.findByDate(start, end);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (tblRestLog.isEmpty()) {
            throw new TBLRestLogNotFounded("Log not found");
        }

        return tblRestLog.stream( ).map(TBLRestLogMapper::convertToDTO).toList();
    }
}
