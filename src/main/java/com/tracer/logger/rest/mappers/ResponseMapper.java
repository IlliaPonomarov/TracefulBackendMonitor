package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.ResponseDTO;
import com.tracer.logger.rest.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


public class ResponseMapper {

    public static ResponseDTO convertToDTO(Response response) {

        return new ResponseDTO(
                response.getStatusCode().toString(),
                response.getHeaders(),
                response.getBody(),
                response.getError(),
                response.getDate().toString()
        );
    }


    public static Response convertToEntity(ResponseDTO responseDTO) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        try {
           statusCode = HttpStatus.valueOf(Integer.parseInt(responseDTO.getStatusCode()));
        }catch (NumberFormatException e){
            responseDTO.setStatusCode("StatusCode is not a convertible number");
            System.out.println("StatusCode is not a convertible number");
        }

        return new Response(
                statusCode,
                responseDTO.getHeaders(),
                responseDTO.getBody(),
                responseDTO.getError()
        );
    }

}
