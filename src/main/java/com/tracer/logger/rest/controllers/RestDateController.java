package com.tracer.logger.rest.controllers;

import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.ServiceNotFoundException;
import com.tracer.logger.rest.exceptions.RestLogNotFounded;
import com.tracer.logger.rest.mappers.RestLogMapper;
import com.tracer.logger.rest.models.RestLog;
import com.tracer.logger.rest.services.RestService;
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
public class RestDateController {

    private final RestService restService;

    @Autowired
    public RestDateController(RestService restService) {
        this.restService = restService;
    }


    @Operation(summary = "Find a log by between start and end dates", tags = {"Rest Log Date Service"})
    @Parameter(in = ParameterIn.PATH, name = "start", required = true, description = "Start date")
    @Parameter(in = ParameterIn.PATH, name = "end", required = true, description = "End date")
    @Parameter(in = ParameterIn.QUERY, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log found" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{start}/{end}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RestLogDTO> findByBetweenDateAndService(@PathVariable String start, @PathVariable String end, @RequestParam String service) {

        List<RestLog> RestLog = null;
        Optional<List<RestLog>> tbmRestLog = restService.findByService(service);

        if (tbmRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            RestLog = restService.findByBetweenDateAndService(start, end, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }

        if (RestLog.isEmpty()) {
            throw new RestLogNotFounded("Log not found");
        }

        return RestLog.stream( ).map(RestLogMapper::convertToDTO).toList();
    }

    @Operation(summary = "Find a log by start date", tags = {"Rest Log Date Service"})
    @Parameter(in = ParameterIn.PATH, name = "start", required = true, description = "Start date")
    @Parameter(in = ParameterIn.QUERY, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log found" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{start}/start")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RestLogDTO> findByDateAndService(@PathVariable String start, @RequestParam String service) {

        List<RestLog> restLogs = null;
        Optional<List<RestLog>> tbmRestLog = restService.findByService(service);

        if (tbmRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            restLogs = restService.findByStartDateAndService(start, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (restLogs.isEmpty()) {
            throw new RestLogNotFounded("Log not found");
        }

        return restLogs.stream( ).map(RestLogMapper::convertToDTO).toList();
    }

    @Operation(summary = "Find a log by end date", tags = {"Rest Log Date Service"})
    @Parameter(in = ParameterIn.PATH, name = "end", required = true, description = "End date")
    @Parameter(in = ParameterIn.QUERY, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log found" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Log not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{end}/end")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RestLogDTO> findByEndDateAndService(@PathVariable String end, @RequestParam String service) {

        List<RestLog> restLogs = null;
        Optional<List<RestLog>> restLog = restService.findByService(service);

        if (restLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        try {
            restLogs = restService.findByEndDateAndService(end, service);
        } catch (DateException e) {
            throw new DateException(e.getMessage());
        }
        if (restLogs.isEmpty()) {
            throw new RestLogNotFounded("Log not found");
        }

        return restLogs.stream( ).map(RestLogMapper::convertToDTO).toList();
    }
}
