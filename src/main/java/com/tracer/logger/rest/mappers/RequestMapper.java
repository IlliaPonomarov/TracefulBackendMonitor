package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.models.Request;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


public class RequestMapper {

        public static RequestDTO convertToDTO(Request request) {
            return new RequestDTO(
                    request.getMethod().toString(),
                    request.getUrl(),
                    request.getBody(),
                    request.getHeaders(),
                    request.getDate().toString()
            );
        }

        public static Request convertToEntity(RequestDTO requestDTO) {
            return new Request(
                    HttpMethod.valueOf(requestDTO.getMethod()),
                    requestDTO.getUrl(),
                    requestDTO.getBody(),
                    requestDTO.getHeaders()
            );
        }
}
