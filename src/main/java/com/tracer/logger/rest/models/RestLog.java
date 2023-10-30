package com.tracer.logger.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.List;

@Document(collection = "rest")
public class TBMRestLog {

    @Id
    private String id;
    private Date dateInit;
    private String service;
    private Request request;
    private Response response;



    public TBMRestLog(String id, Request request, Response response, String service) {
        this.dateInit = new Date();
        this.request = request;
        this.response = response;
        this.service = service;
        this.id = id;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
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
        if (!(o instanceof TBMRestLog TBMRestLog)) return false;
        return Objects.equals(id, TBMRestLog.id) && Objects.equals(dateInit, TBMRestLog.dateInit) && Objects.equals(request, TBMRestLog.request) && Objects.equals(response, TBMRestLog.response) && Objects.equals(service, TBMRestLog.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateInit, request, response, service);
    }

    @Override
    public String toString() {
        return "TBLRest{" +
                "id='" + id + '\'' +
                ", dateInit=" + dateInit +
                ", request=" + request +
                ", response=" + response +
                ", service='" + service + '\'' +
                '}';
    }
}
