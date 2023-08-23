package com.tracer.logger.rest.mappers;

import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.models.Request;
import org.springframework.stereotype.Component;


public class RequestMapper {

        public static RequestDTO convertToDTO(Request request) {
            return new RequestDTO(
                    request.getMethod(),
                    request.getUrl(),
                    request.getBody(),
                    request.getHeaders(),
                    request.getDate()
            );
        }

        public static Request convertToEntity(RequestDTO requestDTO) {
            return new Request(
                    requestDTO.getMethod(),
                    requestDTO.getUrl(),
                    requestDTO.getBody(),
                    requestDTO.getHeaders()
            );
        }
}
