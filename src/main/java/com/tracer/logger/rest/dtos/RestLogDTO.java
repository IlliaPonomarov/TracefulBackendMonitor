package com.tracer.logger.rest.dtos;

import java.util.Date;
import java.util.Objects;

public class RestLogDTO {

    private String id;

    private String service;

    private RequestDTO request;

    private ResponseDTO response;
    private String dateInit;

    public RestLogDTO() {
    }

    public RestLogDTO(String id, RequestDTO requestDTO, ResponseDTO responseDTO, String service) {
        this.request = requestDTO;
        this.response = responseDTO;
        this.service = service;
        this.id = id;
        this.dateInit = new Date().toString();
    }

    public String getId() {
        return id;
    }

    public String getDateInit() {
        return dateInit;
    }

    public void setDateInit(String dateInit) {
        this.dateInit = dateInit;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestLogDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(service, that.service) && Objects.equals(request, that.request) && Objects.equals(response, that.response) && Objects.equals(dateInit, that.dateInit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, service, request, response, dateInit);
    }

    @Override
    public String toString() {
        return "TBMRestLogDTO{" +
                "id='" + id + '\'' +
                ", service='" + service + '\'' +
                ", request=" + request +
                ", response=" + response +
                ", dateInit=" + dateInit +
                '}';
    }
}
