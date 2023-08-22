package com.tracer.logger.rest.dtos;

import java.util.Objects;

public class TBLRestLogDTO {

    private RequestDTO request;
    private ResponseDTO response;

    public TBLRestLogDTO() {
    }

    public TBLRestLogDTO(RequestDTO requestDTO, ResponseDTO responseDTO) {
        this.request = requestDTO;
        this.response = responseDTO;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    public ResponseDTO getResponse() {
        return response;
    }

    public void setResponse(ResponseDTO response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TBLRestLogDTO that)) return false;
        return request.equals(that.request) && response.equals(that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, response);
    }

    @Override
    public String toString() {
        return "TBLRestLogDTO{" +
                "requestDTO=" + request +
                ", responseDTO=" + response +
                '}';
    }
}
