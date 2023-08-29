package com.tracer.logger.rest.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.List;

public class TBMRestLogDTO {

    private String id;

    private String service;

    private List<RequestDTO> request = new ArrayList<>();

    private List<ResponseDTO> response = new ArrayList<>();
    private Date dateInit;

    public TBMRestLogDTO() {
    }

    public TBMRestLogDTO(String id, List<RequestDTO> requestDTO, List<ResponseDTO> responseDTO, String service) {
        this.request = requestDTO;
        this.response = responseDTO;
        this.service = service;
        this.id = id;
        this.dateInit = new Date();
    }

    public String getId() {
        return id;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public List<RequestDTO> getRequest() {
        return request;
    }

    public void setRequest(List<RequestDTO> request) {
        this.request = request;
    }

    public List<ResponseDTO> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseDTO> response) {
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
        if (!(o instanceof TBMRestLogDTO that)) return false;
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