package com.tracer.logger.rest.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tbl/rest")
public class TBLRestController {

    private final TBLRestService tblRestService;


    @Autowired
    public TBLRestController(TBLRestService tblRestService) {
        this.tblRestService = tblRestService;
    }

    @PostMapping("/log")
    public ResponseEntity<HttpEntity> log(@RequestBody TBLLogDTO tblLogDTO, BindingResult bindingResult) {
        tblRestService.log(tblLogDTO);
        return ResponseEntity.ok().build();
    }
}
