package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.exceptions.ServiceNotFoundException;
import com.tracer.logger.rest.exceptions.TBMRestLogBadRequest;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rest")
public class TBMRestMainController {
    private final RestService restService;

    @Autowired
    public TBMRestMainController(RestService restService) {
        this.restService = restService;
    }

    @Operation(summary = "Log a request and response", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.DEFAULT, name = "tbmLogDTO", required = true, description = "TBMRestLogDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log created" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/log")
    @ResponseStatus(HttpStatus.CREATED)
    public TBMRestLogDTO log(@RequestBody TBMRestLogDTO tbmLogDTO, BindingResult bindingResult) {

        TBMRestLogDTO tbmRestLogDTO = null;
        TBMRestLog tbmRestLog = null;

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            errors.forEach(error -> errorMessage.append(error.getDefaultMessage()).append(",\t"));

            throw new TBMRestLogBadRequest(errorMessage.toString());
        }
        tbmRestLog = restService.log(tbmLogDTO);
        tbmRestLogDTO = TBMRestLogMapper.convertToDTO(tbmRestLog);

        return tbmRestLogDTO;
    }

    @Operation(summary = "Find all logs", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.DEFAULT, name = "tbmLogDTO", required = true, description = "Get all logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs found" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<TBMRestLogDTO> findAll() {
        List<TBMRestLog> TBMRestLogs = new ArrayList<>(restService.findAll());

        if (TBMRestLogs.isEmpty())
            throw new TBMRestLogNotFounded("No logs found");

        return TBMRestLogs.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }


    @Operation(summary = "Find logs by service id", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.PATH, name = "service", required = true, description = "Service id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs found" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public TBMRestLogDTO findByUUID(@PathVariable String id) {
        Optional<TBMRestLog> tblRestLog = restService.findById(id);

        if (tblRestLog.isEmpty())
            throw new TBMRestLogNotFounded("Log not found");

        return TBMRestLogMapper.convertToDTO(tblRestLog.get());
    }



    @Operation(summary = "Delete logs by service name", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.PATH, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs deleted" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
            @ApiResponse(responseCode = "404", description = "Logs not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/service/{service}")
    @ResponseStatus(HttpStatus.OK)
    public TBMRestLog deleteByService(@PathVariable String service) {
        Optional<List<TBMRestLog>> tblRestLog = restService.findByService(service);

        if (tblRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        return restService.deleteByService(service);
    }

    @Operation(summary = "Delete all logs", tags = {"Rest Log Service"})
    @Parameter(in = ParameterIn.PATH, name = "service", required = true, description = "Service name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs deleted" , content = @Content(schema = @Schema(implementation = TBMRestLogDTO.class))),
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
