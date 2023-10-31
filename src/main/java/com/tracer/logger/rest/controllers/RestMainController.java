package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.exceptions.ServiceNotFoundException;
import com.tracer.logger.rest.exceptions.TBMRestLogBadRequest;
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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/rest")
public class RestMainController {
    private final RestService restService;

    @Autowired
    public RestMainController(RestService restService) {
        this.restService = restService;
    }

    @Operation(summary = "Log a request and response", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.DEFAULT, name = "tbmLogDTO", required = true, description = "TBMRestLogDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log created" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/log")
    @ResponseStatus(HttpStatus.CREATED)
    public RestLogDTO log(@RequestBody @Valid RestLogDTO restLogDTO, BindingResult bindingResult) {

        RestLog createdRestLog = new RestLog();

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            errors.forEach(error -> errorMessage.append(error.getDefaultMessage()).append(",\t"));

            throw new TBMRestLogBadRequest(errorMessage.toString());
        }
        createdRestLog = restService.log(restLogDTO);

        return RestLogMapper.convertToDTO(createdRestLog);
    }

    @Operation(summary = "Find all logs", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.DEFAULT, name = "tbmLogDTO", required = true, description = "Get all logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs found" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<RestLogDTO> findAll() {
        List<RestLog> RestLogs = new ArrayList<>(restService.findAll());

        if (RestLogs.isEmpty())
            throw new RestLogNotFounded("No logs found");

        return RestLogs.stream( ).map(RestLogMapper::convertToDTO).toList();
    }


    @Operation(summary = "Find logs by service id", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.PATH, name = "service", required = true, description = "Service id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs found" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public RestLogDTO findByUUID(@PathVariable String id) {
        Optional<RestLog> tblRestLog = restService.findById(id);

        if (tblRestLog.isEmpty())
            throw new RestLogNotFounded("Log not found");

        return RestLogMapper.convertToDTO(tblRestLog.get());
    }



    @Operation(summary = "Delete logs by service name", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.PATH, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs deleted" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/service/{service}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<RestLogDTO> deleteByService(@PathVariable String service) {
        Optional<List<RestLog>> filteredRestLogsByService = restService.findByService(service);

        if (filteredRestLogsByService.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        return restService
                .deleteByService(filteredRestLogsByService.get())
                .stream()
                        .map(RestLogMapper::convertToDTO).collect(toList());
    }

    @Operation(summary = "Delete all logs", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.PATH, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs deleted" , content = @Content(schema = @Schema(implementation = RestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAll() {
        restService.deleteAll();

        return "All logs deleted";
    }



}
