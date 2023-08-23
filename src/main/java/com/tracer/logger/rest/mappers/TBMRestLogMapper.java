package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.ResponseDTO;
import com.tracer.logger.rest.dtos.TBLRestLogDTO;
import com.tracer.logger.rest.models.Request;
import com.tracer.logger.rest.models.Response;
import com.tracer.logger.rest.models.TBMRestLog;

public class TBMRestLogMapper {

    public static TBMRestLog convertToEntity(TBLRestLogDTO tblLogDTO) {
        Request request = RequestMapper.convertToEntity(tblLogDTO.getRequest());
        Response response = ResponseMapper.convertToEntity(tblLogDTO.getResponse());

        return new TBMRestLog(
                tblLogDTO.getId(),
                request,
                response,
                tblLogDTO.getService()
        );
    }

    public static TBLRestLogDTO convertToDTO(TBMRestLog tblLog) {
        Request request = tblLog.getRequest();
        Response response = tblLog.getResponse();

        RequestDTO requestDTO = RequestMapper.convertToDTO(request);
        ResponseDTO responseDTO = ResponseMapper.convertToDTO(response);

        return new TBLRestLogDTO(
                tblLog.getId(),
                requestDTO,
                responseDTO,
                tblLog.getService()
        );
    }
}
