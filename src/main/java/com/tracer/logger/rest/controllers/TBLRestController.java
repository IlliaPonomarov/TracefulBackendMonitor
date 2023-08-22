package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.models.TBLRestLog;
import com.tracer.logger.rest.services.TBLRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/tbl/rest")
public class TBLRestController {

    private final TBLRestService tblRestService;


    @Autowired
    public TBLRestController(TBLRestService tblRestService) {
        this.tblRestService = tblRestService;
    }

    @PostMapping("/log")
    public ResponseEntity<HttpStatus> log(@RequestBody TBLRestLogDTO tblLogDTO, BindingResult bindingResult) {

        tblRestService.log(tblLogDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TBLRestLog>> findAll() {
        List<TBLRestLog> tblRestLogs = tblRestService.findAll();
        return new ResponseEntity<>(tblRestLogs, HttpStatus.OK);
    }

}
