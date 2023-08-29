package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.ResponseDTO;
import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import com.tracer.logger.rest.models.Request;
import com.tracer.logger.rest.models.Response;
import com.tracer.logger.rest.models.TBMRestLog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TBMRestLogMapper {

    public static TBMRestLog convertToEntity(TBMRestLogDTO tblLogDTO) {
        List<Request> request = new ArrayList<>(tblLogDTO.getRequest().stream().map(RequestMapper::convertToEntity).toList());
        List<Response> response = new ArrayList<>(tblLogDTO.getResponse().stream().map(ResponseMapper::convertToEntity).toList());

        return new TBMRestLog(
                tblLogDTO.getId(),
                request,
                response,
                tblLogDTO.getService()
        );
    }

    public static TBMRestLogDTO convertToDTO(TBMRestLog tblLog) {
        List<Request> request = tblLog.getRequest();
        List<Response> response = tblLog.getResponse();

        List<RequestDTO> requestDTO = request.stream().map(RequestMapper::convertToDTO).toList();
        List<ResponseDTO> responseDTO = response.stream().map(ResponseMapper::convertToDTO).toList();

        return new TBMRestLogDTO(
                tblLog.getId(),
                requestDTO,
                responseDTO,
                tblLog.getService()
        );
    }
}
