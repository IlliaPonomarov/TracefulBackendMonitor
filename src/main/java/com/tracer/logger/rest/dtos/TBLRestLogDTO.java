package com.tracer.logger.rest.dtos;

import java.util.Date;
import java.util.Objects;

public class TBLRestLogDTO {

    private String id;

    private String service;
    private RequestDTO request;
    private ResponseDTO response;

    private Date dateInit;

    public TBLRestLogDTO() {
    }

    public TBLRestLogDTO(String id, RequestDTO requestDTO, ResponseDTO responseDTO, String service) {
        this.request = requestDTO;
        this.response = responseDTO;
        this.service = service;
        this.id = id;
        this.dateInit = new Date();
    }

    public String getId() {
        return id;
    }

    public void getService(String id) {
        this.id = id;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
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
        if (!(o instanceof TBLRestLogDTO that)) return false;
        return request.equals(that.request) && response.equals(that.response) && service.equals(that.service) && id.equals(that.id) && dateInit.equals(that.dateInit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, response, service, id, dateInit);
    }

    @Override
    public String toString() {
        return "TBLRestLogDTO{" +
                "id='" + id + '\'' +
                ", dateInit=" + dateInit +
                "requestDTO=" + request +
                ", responseDTO=" + response +
                ", service='" + service + '\'' +
                '}';
    }
}
