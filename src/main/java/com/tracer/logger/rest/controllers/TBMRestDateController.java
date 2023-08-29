package com.tracer.logger.rest.controllers;

import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ServiceNotFoundException;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.services.TBMRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rest/date")
public class TBMRestDateController {

    private final TBMRestService tbmRestService;

    @Autowired
    public TBMRestDateController(TBMRestService tbmRestService) {
        this.tbmRestService = tbmRestService;
    }

    @GetMapping("/{start}/{end}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TBMRestLogDTO> findRestLogByBetweenDateAndService(@PathVariable String start, @PathVariable String end, @RequestParam String service) {

        List<TBMRestLog> TBMRestLog = null;
        Optional<TBMRestLog> tbmRestLog = tbmRestService.findByService(service);

        if (tbmRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            TBMRestLog = tbmRestService.findRestLogByBetweenDateAndService(start, end, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (TBMRestLog.isEmpty()) {
            throw new TBMRestLogNotFounded("Log not found");
        }

        return TBMRestLog.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }
}
