package com.tracer.logger.rest.controllers;


import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.mappers.TBLRestLogMapper;
import com.tracer.logger.rest.models.TBLRestLog;
import com.tracer.logger.rest.services.TBLRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RestController
@RequestMapping("/tbl/rest")
public class TBLRestController {

    private final TBLRestService tblRestService;


    @Autowired
    public TBLRestController(TBLRestService tblRestService) {
        this.tblRestService = tblRestService;
    }

    @PostMapping("/log")
    public ResponseEntity<TBLRestLogDTO> log(@RequestBody TBLRestLogDTO tblLogDTO, BindingResult bindingResult) {

        tblRestService.log(tblLogDTO);

        return new ResponseEntity<>(tblLogDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TBLRestLogDTO>> findAll() {
        List<TBLRestLog> tblRestLogs = tblRestService.findAll();
        List<TBLRestLogDTO> tblRestLogDTOList = tblRestLogs.stream().map(TBLRestLogMapper::convertToDTO).toList();

        return new ResponseEntity<>(tblRestLogDTOList, HttpStatus.OK);
    }

}
