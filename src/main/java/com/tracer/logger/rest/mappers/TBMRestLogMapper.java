package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.ResponseDTO;
import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.models.Request;
import com.tracer.logger.rest.models.Response;
import com.tracer.logger.rest.models.RestLog;

public class TBMRestLogMapper {

    public static RestLog convertToEntity(TBMRestLogDTO tblLogDTO) {
        Request request = RequestMapper.convertToEntity(tblLogDTO.getRequest());
        Response response = ResponseMapper.convertToEntity(tblLogDTO.getResponse());

        return new RestLog(
                tblLogDTO.getId(),
                request,
                response,
                tblLogDTO.getService()
        );
    }

    public static TBMRestLogDTO convertToDTO(RestLog tblLog) {
        RequestDTO requestDTO = RequestMapper.convertToDTO(tblLog.getRequest());
        ResponseDTO responseDTO = ResponseMapper.convertToDTO(tblLog.getResponse());

        return new TBMRestLogDTO(
                tblLog.getId(),
                requestDTO,
                responseDTO,
                tblLog.getService()
        );
    }
}
