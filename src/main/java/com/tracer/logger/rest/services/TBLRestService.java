package com.tracer.logger.rest.services;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.mappers.TBLRestLogMapper;
import com.tracer.logger.rest.models.TBLRestLog;
import com.tracer.logger.rest.repos.TBLRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TBLRestService {

    private final TBLRestRepo tblRestRepo;

    @Autowired
    public TBLRestService(TBLRestRepo tblRestRepo) {
        this.tblRestRepo = tblRestRepo;
    }


    public TBLRestLog log(TBLRestLogDTO tblRestLogDTO) {
        TBLRestLog tblRestLog = TBLRestLogMapper.convertToEntity(tblRestLogDTO);

        return tblRestRepo.save(tblRestLog);
    }

    public List<TBLRestLog> findAll() {
        return tblRestRepo.findAll();
    }

}
