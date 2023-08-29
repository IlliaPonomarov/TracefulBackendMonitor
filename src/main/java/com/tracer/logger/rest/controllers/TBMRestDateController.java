package com.tracer.logger.rest.controllers;

import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ServiceNotFoundException;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.services.TBMRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Find a log by between start and end dates", tags = {"Rest Log Date Service"})
    @Parameter(in = ParameterIn.PATH, name = "start", required = true, description = "Start date")
    @Parameter(in = ParameterIn.PATH, name = "end", required = true, description = "End date")
    @Parameter(in = ParameterIn.QUERY, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log found" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{start}/{end}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TBMRestLogDTO> findByBetweenDateAndService(@PathVariable String start, @PathVariable String end, @RequestParam String service) {

        List<TBMRestLog> TBMRestLog = null;
        Optional<List<TBMRestLog>> tbmRestLog = tbmRestService.findByService(service);

        if (tbmRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            TBMRestLog = tbmRestService.findByBetweenDateAndService(start, end, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (TBMRestLog.isEmpty()) {
            throw new TBMRestLogNotFounded("Log not found");
        }

        return TBMRestLog.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }

    @Operation(summary = "Find a log by start date", tags = {"Rest Log Date Service"})
    @Parameter(in = ParameterIn.PATH, name = "start", required = true, description = "Start date")
    @Parameter(in = ParameterIn.QUERY, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log found" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{start}/start")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TBMRestLogDTO> findByDateAndService(@PathVariable String start, @RequestParam String service) {

        List<TBMRestLog> tbmRestLogs = null;
        Optional<List<TBMRestLog>> tbmRestLog = tbmRestService.findByService(service);

        if (tbmRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            tbmRestLogs = tbmRestService.findByStartDateAndService(start, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (tbmRestLogs.isEmpty()) {
            throw new TBMRestLogNotFounded("Log not found");
        }

        return tbmRestLogs.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }

    @Operation(summary = "Find a log by end date", tags = {"Rest Log Date Service"})
    @Parameter(in = ParameterIn.PATH, name = "end", required = true, description = "End date")
    @Parameter(in = ParameterIn.QUERY, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log found" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{end}/end")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TBMRestLogDTO> findByEndDateAndService(@PathVariable String end, @RequestParam String service) {

        List<TBMRestLog> tbmRestLogs = null;
        Optional<List<TBMRestLog>> tbmRestLog = tbmRestService.findByService(service);

        if (tbmRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            tbmRestLogs = tbmRestService.findByEndDateAndService(end, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (tbmRestLogs.isEmpty()) {
            throw new TBMRestLogNotFounded("Log not found");
        }

        return tbmRestLogs.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }
}
