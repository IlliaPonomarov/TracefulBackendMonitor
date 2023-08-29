package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.exceptions.ServiceNotFoundException;
import com.tracer.logger.rest.exceptions.TBMRestLogBadRequest;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.TBMRestLog;
import com.tracer.logger.rest.services.TBMRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rest")
public class TBMRestMainController {

    private final TBMRestService tbmRestService;


    @Autowired
    public TBMRestMainController(TBMRestService tbmRestService) {
        this.tbmRestService = tbmRestService;
    }

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
        tbmRestLog = tbmRestService.log(tbmLogDTO);
        tbmRestLogDTO = TBMRestLogMapper.convertToDTO(tbmRestLog);

        return tbmRestLogDTO;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<TBMRestLogDTO> findAll() {
        List<TBMRestLog> TBMRestLogs = new ArrayList<>(tbmRestService.findAll());

        if (TBMRestLogs.isEmpty()) {
            throw new TBMRestLogNotFounded("No logs found");
        }

        return TBMRestLogs.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public TBMRestLogDTO findByUUID(@PathVariable UUID id) {
        Optional<TBMRestLog> tblRestLog = tbmRestService.findById(id);

        if (tblRestLog.isEmpty())
            throw new TBMRestLogNotFounded("Log not found");

        return TBMRestLogMapper.convertToDTO(tblRestLog.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UUID deleteById(@PathVariable UUID id) {
        Optional<TBMRestLog> tblRestLog = tbmRestService.findById(id);

        if (tblRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", id));

        tbmRestService.deleteById(id);

        return id;
    }

    @DeleteMapping("/{service}")
    @ResponseStatus(HttpStatus.OK)
    public TBMRestLog deleteByService(@PathVariable String service) {
        Optional<TBMRestLog> tblRestLog = tbmRestService.findByService(service);

        if (tblRestLog.isEmpty())
            throw new ServiceNotFoundException(String.format("Service %s not found", service));

        return tbmRestService.deleteByService(service);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() {
        tbmRestService.deleteAll();
    }



}
