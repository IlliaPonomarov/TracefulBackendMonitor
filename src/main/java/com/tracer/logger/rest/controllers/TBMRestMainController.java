package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.TBLRestLogDTO;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rest")
public class TBMRestMainController {

    private final TBMRestService TBMRestService;


    @Autowired
    public TBMRestMainController(TBMRestService TBMRestService) {
        this.TBMRestService = TBMRestService;
    }

    @PostMapping("/log")
    @ResponseStatus(HttpStatus.CREATED)
    public TBLRestLogDTO log(@RequestBody TBLRestLogDTO tblLogDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();

            errors.forEach(error -> errorMessage.append(error.getDefaultMessage()).append(",\t"));

            throw new TBMRestLogBadRequest(errorMessage.toString());
        }

        TBMRestService.log(tblLogDTO);

        return tblLogDTO;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<TBLRestLogDTO> findAll() {
        List<TBMRestLog> TBMRestLogs = TBMRestService.findAll();

        if (TBMRestLogs.isEmpty()) {
            throw new TBMRestLogNotFounded("No logs found");
        }

        return TBMRestLogs.stream( ).map(TBMRestLogMapper::convertToDTO).toList();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.FOUND)
    public TBLRestLogDTO findByUuid(@PathVariable String uuid) {
        Optional<TBMRestLog> tblRestLog = TBMRestService.findByUUID(uuid);

        if (tblRestLog.isEmpty()) {
            throw new TBMRestLogNotFounded("Log not found");
        }

        return TBMRestLogMapper.convertToDTO(tblRestLog.get());
    }




}
