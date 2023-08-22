package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.ResponseDTO;
import com.tracer.logger.rest.models.Response;


public class ResponseMapper {

    public static ResponseDTO convertToDTO(Response response) {

        return new ResponseDTO(
                response.getStatusCode(),
                response.getHeaders(),
                response.getBody(),
                response.getError()
        );
    }


    public static Response convertToEntity(ResponseDTO responseDTO) {
        return new Response(
                responseDTO.getStatusCode(),
                responseDTO.getHeaders(),
                responseDTO.getBody(),
                responseDTO.getError()
        );
    }

}
