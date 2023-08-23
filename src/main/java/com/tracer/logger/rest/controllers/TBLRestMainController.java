package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.exceptions.DateException;
import com.tracer.logger.rest.exceptions.TBLRestLogBadRequest;
import com.tracer.logger.rest.exceptions.TBLRestLogNotFounded;
import com.tracer.logger.rest.mappers.TBLRestLogMapper;
import com.tracer.logger.rest.models.TBLRestLog;
import com.tracer.logger.rest.services.TBLRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rest")
public class TBLRestMainController {

    private final TBLRestService tblRestService;


    @Autowired
    public TBLRestMainController(TBLRestService tblRestService) {
        this.tblRestService = tblRestService;
    }

    @PostMapping("/log")
    @ResponseStatus(HttpStatus.CREATED)
    public TBLRestLogDTO log(@RequestBody TBLRestLogDTO tblLogDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            errors.forEach(error -> errorMessage.append(error.getDefaultMessage()).append(",\t"));

            throw new TBLRestLogBadRequest(errorMessage.toString());
        }

        tblRestService.log(tblLogDTO);

        return tblLogDTO;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<TBLRestLogDTO> findAll() {
        List<TBLRestLog> tblRestLogs = tblRestService.findAll();

        if (tblRestLogs.isEmpty()) {
            throw new TBLRestLogNotFounded("No logs found");
        }

        return tblRestLogs.stream( ).map(TBLRestLogMapper::convertToDTO).toList();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.FOUND)
    public TBLRestLogDTO findByUuid(@PathVariable String uuid) {
        Optional<TBLRestLog> tblRestLog = tblRestService.findByUUID(uuid);

        if (tblRestLog.isEmpty()) {
            throw new TBLRestLogNotFounded("Log not found");
        }

        return TBLRestLogMapper.convertToDTO(tblRestLog.get());
    }




}
